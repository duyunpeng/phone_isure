package phoneisure.infrastructure.persistence.hibernate.sequence;

import org.springframework.stereotype.Repository;
import phoneisure.domain.model.sequence.ISequenceRepository;
import phoneisure.domain.model.sequence.Sequence;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/22.
 */
@Repository("sequenceRepository")
public class SequenceRepository extends AbstractHibernateGenericRepository<Sequence,String>
        implements ISequenceRepository<Sequence,String> {
}
