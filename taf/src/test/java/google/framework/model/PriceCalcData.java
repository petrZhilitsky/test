package google.framework.model;

import java.util.Objects;

public class PriceCalcData {
    private String commitmentTerm;
    private String totalCost;

    public PriceCalcData(String commitmentTerm, String totalCost) {
        this.commitmentTerm = commitmentTerm;
        this.totalCost = totalCost;
    }

    public String getCommitmentTerm() {
        return commitmentTerm;
    }

    public void setCommitmentTerm(String commitmentTerm) {
        this.commitmentTerm = commitmentTerm;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "PriceCalcData{" +
                "commitmentTerm='" + commitmentTerm + '\'' +
                ", totalCost='" + totalCost + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceCalcData that = (PriceCalcData) o;
        return Objects.equals(getCommitmentTerm(), that.getCommitmentTerm()) &&
                Objects.equals(getTotalCost(), that.getTotalCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommitmentTerm(), getTotalCost());
    }
}
