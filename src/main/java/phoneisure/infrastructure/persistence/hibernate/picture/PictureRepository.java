package phoneisure.infrastructure.persistence.hibernate.picture;

import org.springframework.stereotype.Repository;
import phoneisure.domain.model.picture.IPictureRepository;
import phoneisure.domain.model.picture.Picture;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/12.
 */
@Repository("pictureRepository")
public class PictureRepository extends AbstractHibernateGenericRepository<Picture, String>
        implements IPictureRepository<Picture, String> {
}
