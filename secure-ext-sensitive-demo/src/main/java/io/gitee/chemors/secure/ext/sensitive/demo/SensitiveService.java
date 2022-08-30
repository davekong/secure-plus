package io.gitee.chemors.secure.ext.sensitive.demo;

import com.chemors.Page;
import io.gitee.chemors.secure.ext.annotations.Desensitization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SensitiveService {
    @Desensitization
    public SensitiveEntity simpleData() {
        SensitiveEntity sensitiveEntity = createEntity("1", "樱木花道", "15699996666", "101010200001010001");
        return sensitiveEntity;
    }

    @Desensitization
    public List<SensitiveEntity> listData() {
        System.out.println("执行业务处理...");
        List<SensitiveEntity> list = new ArrayList<SensitiveEntity>();
        for (int i = 0; i < 5; i++) {
            SensitiveEntity sensitiveEntity = createEntity("1" + i, "樱木花道" + i, "15699996666" + i, "10101020000101000" + i);
            list.add(sensitiveEntity);
        }
        return list;
    }

    @Desensitization
    public Page page() {
        Page page = new Page();
        page.setCurrent(1);
        page.setSize(10);
        page.setRecords(listData());
        return page;
    }

    private static SensitiveEntity createEntity(String i, String i1, String i2, String i3) {
        SensitiveEntity sensitiveEntity = new SensitiveEntity();
        sensitiveEntity.setId(i);
        sensitiveEntity.setName(i1);
        sensitiveEntity.setMobile(i2);
        sensitiveEntity.setIdCard(i3);
        List<SensitiveSubEntity> subEntities = new ArrayList<SensitiveSubEntity>();
        for (int j=0;j<3;j++){
            SensitiveSubEntity sensitiveSubEntity = new SensitiveSubEntity();
            sensitiveSubEntity.setAddress("addr--->"+j);

            SensitiveGrandSonEntity grandSonEntity = new SensitiveGrandSonEntity();
            grandSonEntity.setEmail("grq100296@163.com");
            sensitiveSubEntity.setSensitiveGrandSonEntity(grandSonEntity);

            subEntities.add(sensitiveSubEntity);
        }
        sensitiveEntity.setSubEntities(subEntities);
        return sensitiveEntity;
    }


}
