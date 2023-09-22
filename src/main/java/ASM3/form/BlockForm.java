package ASM3.form;

public class BlockForm {
    private int userId;
    private String description;
    private int decide; //0 : Block, 1 : unBlock
    private int roleID;
    private boolean isOK = false;



    public BlockForm() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDecide() {
        return decide;
    }

    public void setDecide(int decide) {
        this.decide = decide;
    }
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

}
