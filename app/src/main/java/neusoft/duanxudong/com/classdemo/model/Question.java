package neusoft.duanxudong.com.classdemo.model;


import java.util.Date;
import java.util.Set;




public class Question {
    private long qid;
    private String asktitle;
    private String askContent;
    private Date add_ask_date;
    /**
     * @return the add_ask_date
     */


    private Set<Answer> answer_s;


    public Set<Answer> getAnswer() {
        return answer_s;
    }

    public void setAnswer(Set<Answer> answer_s) {
        this.answer_s = answer_s;
    }


    /**
     * @return the qid
     */
    public long getQid() {
        return qid;
    }

    /**
     * @param qid the qid to set
     */
    public void setQid(long qid) {
        this.qid = qid;
    }

    /**
     * @return the asktitle
     */
    public String getAsktitle() {
        return asktitle;
    }

    /**
     * @param asktitle the asktitle to set
     */
    public void setAsktitle(String asktitle) {
        this.asktitle = asktitle;
    }

    /**
     * @return the askContent
     */
    public String getAskContent() {
        return askContent;
    }

    /**
     * @param askContent the askContent to set
     */
    public void setAskContent(String askContent) {
        this.askContent = askContent;
    }


    public Date getAdd_ask_date() {

        return add_ask_date;
    }

    /**
     * @param add_ask_date the add_ask_date to set
     */
    public void setAdd_ask_date(Date add_ask_date) {
        this.add_ask_date = add_ask_date;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}