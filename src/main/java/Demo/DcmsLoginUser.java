package Demo;

import java.io.Serializable;

/**
 * ƽ���˺Ź���ҵ���ߺͷ�����
 */
public class DcmsLoginUser implements Serializable {
    private String serviceLine; // SERVICE_LINE       ҵ���ߡ�
    private String branchCenter;  // BRANCH_CENTER  ������

    private static final long serialVersionUID = 5365576551739251483L;

    public DcmsLoginUser() {
        super();
    }

    public DcmsLoginUser(String serviceLine, String branchCenter) {
        this.serviceLine = serviceLine;
        this.branchCenter = branchCenter;
    }

    public String getServiceLine() {
        return serviceLine;
    }

    public void setServiceLine(String serviceLine) {
        this.serviceLine = serviceLine;
    }

    public String getBranchCenter() {
        return branchCenter;
    }

    public void setBranchCenter(String branchCenter) {
        this.branchCenter = branchCenter;
    }

    @Override
    public String toString() {
        return "DcmsLoginUser{" +
                "serviceLine='" + serviceLine + '\'' +
                ", branchCenter='" + branchCenter + '\'' +
                '}';
    }
}
