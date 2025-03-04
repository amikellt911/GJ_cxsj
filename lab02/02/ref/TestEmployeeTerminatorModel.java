import junit.framework.TestCase;
import junit.swingui.TestRunner;
import java.util.Vector;
public class TestEmployeeTerminatorModel extends TestCase
                 implements EmployeeTerminatorView{
				 private boolean terminateEnabled=true;
				 private String selectedEmployee;
				 private Vector noEmployees=new Vector();
				 private Vector threeEmployees=new Vector();
				 private Vector employees=null;
				 private EmployeeTerminatorModel m;
				 public static void main(String[] args) {
				    TestRunner.main(new String[]{"TestEmployeeTerminatorModel"});
                 }
				 
				 public TestEmployeeTerminatorModel(String name) {
                    super(name);
				 }
				 
                 public void setUp() throws Exception {
                    m = new EmployeeTerminatorModel();
                    threeEmployees.add("Bob");
                    threeEmployees.add("Bill");
                    threeEmployees.add("Robert");
				}
				
                 public void tearDown() throws Exception {
                }
				
                 public void testNoEmployees() throws Exception {
                     m.initialize(noEmployees, this);
                     assertEquals(0, employees.size());
                     assertEquals(false, terminateEnabled);
                     assertEquals(null, selectedEmployee);
				}
				
				public void testThreeEmployees() throws Exception {
                    m.initialize(threeEmployees, this);
                    assertEquals(3, employees.size());
                    assertEquals(false, terminateEnabled);
                    assertEquals(null, selectedEmployee);
				}
				
                public void testSelection() throws Exception {
                    m.initialize(threeEmployees, this);
                    m.selectionChanged("Bob");
                    assertEquals(true, terminateEnabled);
                    m.selectionChanged(null);
                    assertEquals(false, terminateEnabled);
				}
				
                public void testTerminate() throws Exception {
                    m.initialize(threeEmployees, this);
                    assertEquals(3, employees.size());
                    selectedEmployee = "Bob";
					m.selectionChanged("Bob");
                    m.terminate();
                    assertEquals(2, employees.size());
                    assertEquals(null, selectedEmployee);
                    assertEquals(false, terminateEnabled);
                    assert(employees.contains("Bill"));
                    assert(employees.contains("Robert"));
                    assert(!employees.contains("Bob"));
                }
				
				// EmployeeTerminatorView interface
                public void enableTerminate(boolean enable) {
                    terminateEnabled = enable;
				}
				
				public void setEmployeeList(Vector employees){
				    this.employees=(Vector) employees.clone();
				}
				
				public void clearSelection(){
				    selectedEmployee=null;
				}
			}
