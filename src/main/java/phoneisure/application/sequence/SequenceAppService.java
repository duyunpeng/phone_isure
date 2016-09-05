package phoneisure.application.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.domain.service.sequence.ISequenceService;

/**
 * Created by YJH on 2016/3/22.
 */
@Service("sequenceAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class SequenceAppService implements ISequenceAppService {

    @Autowired
    private ISequenceService sequenceService;

    @Override
    public long getNextSequence(int suffixLength){
        return sequenceService.getNextSequence(suffixLength);
    }

    @Override
    public void initSequence(){
        sequenceService.initSequence();
    }

    @Override
    public void reset() {
        sequenceService.reset();
    }

}
