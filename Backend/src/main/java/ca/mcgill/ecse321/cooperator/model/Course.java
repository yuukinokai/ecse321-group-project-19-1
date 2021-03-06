package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    private Integer courseId;
    private String courseName;
    private Set<CoopPosition> coopPosition = new HashSet<>();

    public void setCourseId(Integer value) {
        this.courseId = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseName(String value) {
        this.courseName = value;
    }

    public String getCourseName() {
        return this.courseName;
    }

    @ManyToMany
    public Set<CoopPosition> getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(Set<CoopPosition> coopPositions) {
        this.coopPosition = coopPositions;
    }

    public void addAssociatedCoop(CoopPosition cp){
        this.coopPosition.add(cp);
    }
    @Override
    public String toString() {
        return "Course(id= "+getCourseId()+")";
    }
}
