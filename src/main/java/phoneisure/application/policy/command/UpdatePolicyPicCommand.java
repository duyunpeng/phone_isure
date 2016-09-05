package phoneisure.application.policy.command;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by yjh
 * Date : 2016/8/17.
 */
public class UpdatePolicyPicCommand {

    private String id;
    private Integer version;

    @NotNull(message = "{policy.insuredAfterPicture.NotNull.message}")
    private List<String> pics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
