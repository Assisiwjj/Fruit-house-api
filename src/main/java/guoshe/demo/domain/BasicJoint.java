package guoshe.demo.domain;

import java.io.Serializable;

/**
 * @author 
 */
public class BasicJoint implements Serializable {
    private Integer id;

    private Double dip;

    private Double dipAngle;

    private Double density;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDip() {
        return dip;
    }

    public void setDip(Double dip) {
        this.dip = dip;
    }

    public Double getDipAngle() {
        return dipAngle;
    }

    public void setDipAngle(Double dipAngle) {
        this.dipAngle = dipAngle;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BasicJoint other = (BasicJoint) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDip() == null ? other.getDip() == null : this.getDip().equals(other.getDip()))
            && (this.getDipAngle() == null ? other.getDipAngle() == null : this.getDipAngle().equals(other.getDipAngle()))
            && (this.getDensity() == null ? other.getDensity() == null : this.getDensity().equals(other.getDensity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDip() == null) ? 0 : getDip().hashCode());
        result = prime * result + ((getDipAngle() == null) ? 0 : getDipAngle().hashCode());
        result = prime * result + ((getDensity() == null) ? 0 : getDensity().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dip=").append(dip);
        sb.append(", dipAngle=").append(dipAngle);
        sb.append(", density=").append(density);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}