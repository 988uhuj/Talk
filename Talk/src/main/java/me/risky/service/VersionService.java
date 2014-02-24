package me.risky.service;

import me.risky.dao.VersionDao;
import me.risky.domain.Version;
import me.risky.domain.VersionExample;
import me.risky.persistence.VersionMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenupt@gmail.com on 14-2-24.
 * Description : TODO
 */
@Service
public class VersionService implements VersionDao{

    private static final Logger logger = Logger.getLogger(VersionService.class);

    @Autowired
    private VersionMapper versionMapper;

    @Override
    public int createNewVersion(Version version) {
        int id =  versionMapper.insert(version);
        logger.info(id);
        return id;
    }

    @Override
    public void deleteVersion(int versionId) {

    }

    @Override
    public void updateVersion(Version version) {

    }

    @Override
    public Version queryVersion(int versionId) {
        return null;
    }

    @Override
    public Version queryLastVersion() {
        List<Version> list = versionMapper.selectByExample(new VersionExample());
        if(list.size() > 0){
            return list.get(list.size()-1);
        }
        return null;
    }
}
