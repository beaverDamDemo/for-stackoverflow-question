
import com.learning.cam_models.youtubeDev2ProdCoding.project.Project;
import com.learning.cam_models.youtubeDev2ProdCoding.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void saveEmployee(Employee empObj) {
        employeeRepository.save(empObj);
    }

    public List<Employee> getEmployeeDetails(Long empId) {
        if (null != empId) {
            return employeeRepository.findAllByEmpId(empId);
        } else {
            return employeeRepository.findAll();
        }
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

    public Employee assignProjectToEmployee(Long empId, Long projectId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + empId));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));

        Set<Project> projectSet = employee.getAssignedProjects();
        if (projectSet == null) {
            projectSet = new HashSet<>();
        }

        projectSet.add(project);
        employee.setAssignedProjects(projectSet);

        return employeeRepository.save(employee);
    }
}
