package phoneisure.domain.service.picture;


import phoneisure.application.picture.command.CreatePictureCommand;
import phoneisure.domain.model.picture.Picture;

/**
 * Created by YJH on 2016/4/12.
 */
public interface IPictureService {
    Picture create(CreatePictureCommand command);

    Picture searchBuID(String id);

    void delete(String id);
}
