package course.springadvanced.cookeasy.service.impl;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.GenderNameEnum;
import course.springadvanced.cookeasy.repository.GenderRepository;
import course.springadvanced.cookeasy.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public void initializeGenders() {
        if(this.genderRepository.count() != 0) return;

        GenderEntity male = new GenderEntity();
        male.setGenderNameEnum(GenderNameEnum.MALE);

        this.genderRepository.saveAndFlush(male);

        GenderEntity female = new GenderEntity();
        female.setGenderNameEnum(GenderNameEnum.FEMALE);

        this.genderRepository.saveAndFlush(female);
    }
}