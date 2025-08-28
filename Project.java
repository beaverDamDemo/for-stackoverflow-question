import com.learning.cam_models.youtubeDev2ProdCoding.employee.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "youtube-dev-2-prod-coding-PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String projectName;

    @JsonBackReference
    @ManyToMany(mappedBy = "assignedProjects")
    private Set<Employee> employeeSet = new HashSet<>();
}
