package me.risky.web;

import me.risky.base.BaseController;
import me.risky.domain.Version;
import me.risky.service.VersionService;
import me.risky.util.JsonUtil;
import me.risky.util.MapHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by chenupt@gmail.com on 14-2-21.
 * Description : TODO
 */

@Controller
@RequestMapping("version")
public class VersionController extends BaseController{

    private static final Logger logger = Logger.getLogger(VersionController.class);

    // 部署目录，修改为自己的
    @Value("${deploy.path}")
    private String deployPath;

    // 上传目录，修改为自己的
    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    private VersionService versionDao;

    @RequestMapping("data")
    protected ModelAndView testDB(ModelAndView mav){
        logger.info("data");
        mav.setViewName("Test");
        return mav;
    }

    @RequestMapping("query/last")
    protected void queryNewVersion(HttpServletResponse response){
        Version version = versionDao.queryLastVersion();
        logger.info(version.getName());
        setResponseObject(version);
        setResponseMessage("success");
        writeResponse(response);
    }

    @RequestMapping("query/last/code")
    protected void queryNewVersionCode(HttpServletResponse response){
        Version version = versionDao.queryLastVersion();
        logger.info(version.getCode());
        setResponseObject(version.getCode());
        setResponseMessage("success");
        writeResponse(response);
    }


    /**
     *  下载文件替换为原文件名
     */
    @RequestMapping("/download/apk/{fileName}")
    protected void downloadFile( @PathVariable String fileName, HttpServletResponse response) throws IOException {
        logger.info("downloadFile = " + fileName);
        OutputStream os = response.getOutputStream();
        try {
            response.reset();
            // 转化为UTF-8编码
            // TODO 分隔出文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream; charset=utf-8");

            os.write(FileUtils.readFileToByteArray(new File(uploadPath + "/" + fileName)));
            os.flush();
            logger.info("path = " + uploadPath + "/" + fileName);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
                logger.info("download success");
            }
        }
    }

    @RequestMapping("createNewVersion")
    protected void createNewVersion(String param){
        Map<String, Object> paramMap = JsonUtil.fromJsonToObject(param, Map.class);
        logger.info(paramMap);
        String action = MapHelper.s(paramMap, "action");

        
    }

}
