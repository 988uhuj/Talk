package me.risky.dao;

import me.risky.domain.Version;

/**
 * Created by chenupt@gmail.com on 14-2-24.
 * Description : TODO
 */
public interface VersionDao {
    public int createNewVersion(Version version);
    public void deleteVersion(int versionId);
    public void updateVersion(Version version);
    public Version queryVersion(int versionId);
    public Version queryLastVersion();


}
