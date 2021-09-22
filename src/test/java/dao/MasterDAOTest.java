package dao;

import com.example.demo_beauty_saloon.dao.MasterDao;
import com.example.demo_beauty_saloon.entity.Master;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class MasterDAOTest {
    static MasterDao masterDao;
    static Master master;

    @Test
    public void deleteMasterTest() {
        masterDao = new MasterDao();
        Master newMaster = new Master();
        newMaster.setLogin("_");
        newMaster.setPassword("_");
        newMaster.setNameEn("_");
        newMaster.setNameRu("_");
        newMaster.setRating(1);
        newMaster.setInformationEn("_");
        newMaster.setInformationRu("_");
        newMaster.setServiceId(1);
        masterDao.registerMaster(newMaster);
        masterDao.deleteMaster(masterDao.findMasterIdByLogin(newMaster.getLogin()));
        assertNotEquals(1, masterDao.findMasterIdByLogin(newMaster.getLogin()));
    }

    @Test
    public void mastersListTest() {
        masterDao = new MasterDao();
        Master newMaster = new Master();
        newMaster.setLogin("_");
        newMaster.setPassword("_");
        newMaster.setNameEn("_");
        newMaster.setNameRu("_");
        newMaster.setRating(1);
        newMaster.setInformationEn("_");
        newMaster.setInformationRu("_");
        newMaster.setServiceId(1);
        List<Master> mastersPrev = masterDao.selectAllMasters();
        masterDao.registerMaster(newMaster);
        List<Master> masters = masterDao.selectAllMasters();
        masterDao.deleteMaster(masterDao.findMasterIdByLogin(newMaster.getLogin()));
        assertNotEquals(mastersPrev.size(), masters.size());
    }
}
