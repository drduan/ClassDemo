package neusoft.duanxudong.com.classdemo.model;

import java.util.List;


public class SubjectListModel {
    /**
     * @return the list
     */
    public List<subject> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<subject> list) {
        this.list = list;
    }

    private List<subject> list;

    public List<subject> getRecommand() {
        return recommand;
    }

    public void setRecommand(List<subject> recommand) {
        this.recommand = recommand;
    }

    private List<subject> recommand;
}
