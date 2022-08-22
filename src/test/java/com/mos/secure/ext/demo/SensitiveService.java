package com.mos.secure.ext.demo;

import com.mos.secure.ext.annotations.Desensitization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SensitiveService {
    @Desensitization
    public SensitiveEntity simpleData() {
        SensitiveEntity sensitiveEntity = new SensitiveEntity();
        sensitiveEntity.setId("1");
        sensitiveEntity.setName("樱木花道");
        sensitiveEntity.setMobile("15699996666");
        sensitiveEntity.setIdCard("101010200001010001");
        return sensitiveEntity;
    }

    @Desensitization
    public List<SensitiveEntity> listData() {
        System.out.println("执行业务处理...");
        List<SensitiveEntity> list = new ArrayList<SensitiveEntity>();
        for (int i = 0; i < 5; i++) {

            SensitiveEntity sensitiveEntity = new SensitiveEntity();
            sensitiveEntity.setId("1" + i);
            sensitiveEntity.setName("樱木花道" + i);
            sensitiveEntity.setMobile("15699996666" + i);
            sensitiveEntity.setIdCard("10101020000101000" + i);
            List<SensitiveSubEntity> subEntities = new ArrayList<SensitiveSubEntity>();
            for (int j=0;j<3;j++){
                SensitiveSubEntity sensitiveSubEntity = new SensitiveSubEntity();
                sensitiveSubEntity.setAddress("addr--->"+j);
                subEntities.add(sensitiveSubEntity);
            }
            sensitiveEntity.setSubEntities(subEntities);
            list.add(sensitiveEntity);
        }
        return list;
    }


}
