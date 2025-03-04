import junit.framework.TestCase;
import junit.swingui.TestRunner;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class TestEmployeeTerminatorDialog extends TestCase
    implements EmployeeTerminatorController
	{
        private EmployeeTerminatorDialog terminator;
		private JList list;
        private JButton button;
        private Container contentPane;
        private String selectedValue = null;
        private int selectionCount = 0;
        private int terminations = 0;
		
		public static void main(String[] args) {
            TestRunner.main(new String[]{"TestEmployeeTerminatorDialog"});
        }
		
        public TestEmployeeTerminatorDialog(String name) {
            super(name);
        }
		
		public void setUp() throws Exception {
            terminator = new EmployeeTerminatorDialog();
            terminator.initialize(this);
            putComponentsIntoMemberVariables();
		}
		
		private void putComponentsIntoMemberVariables() {
            contentPane = terminator.getContentPane();
            HashMap map = new HashMap();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
               Component c = contentPane.getComponent(i);
               map.put(c.getName(), c);
            }
            list = (JList) map.get(EmployeeTerminatorDialog.EMPLOYEE_LIST_NAME);
            button = (JButton) map.get(EmployeeTerminatorDialog.TERMINATE_BUTTON_NAME);
		}
		
		private void putThreeEmployeesIntoTerminator() {
            Vector v = new Vector();
            v.add("Bob");
            v.add("Bill");
            v.add("Boris");
            terminator.setEmployeeList(v);
		}
		
		public void testCreate() throws Exception {
            assertNotNull(contentPane);
			assertEquals(2, contentPane.getComponentCount());
            assertNotNull(list);
            assertNotNull(button);
            assertEquals(false, button.isEnabled());
		}
		
        public void testAddOneName() throws Exception {
            Vector v = new Vector();
            v.add("Bob");
            terminator.setEmployeeList(v);
            ListModel m = list.getModel();
            assertEquals(1, m.getSize());
            assertEquals("Bob", m.getElementAt(0));
		}
		
		public void testAddManyNames() throws Exception {
            putThreeEmployeesIntoTerminator();
            ListModel m = list.getModel();
            assertEquals(3, m.getSize());
            assertEquals("Bob", m.getElementAt(0));
            assertEquals("Bill", m.getElementAt(1));
            assertEquals("Boris", m.getElementAt(2));
		}
		
        public void testEnableTerminate() throws Exception {
            terminator.enableTerminate(true);
            assertEquals(true, button.isEnabled());
            terminator.enableTerminate(false);
            assertEquals(false, button.isEnabled());
		}
		
		public void testClearSelection() throws Exception {
            putThreeEmployeesIntoTerminator();
            list.setSelectedIndex(1);
            assertNotNull(list.getSelectedValue());
            terminator.clearSelection();
            assertEquals(null, list.getSelectedValue());
		}
		
        public void testSelectionChangedCallback() throws Exception {
            putThreeEmployeesIntoTerminator();
            list.setSelectedIndex(1);
            assertEquals("Bill", selectedValue);
            assertEquals(1, selectionCount);
			list.setSelectedIndex(2);
            assertEquals("Boris", selectedValue);
            assertEquals(2, selectionCount);
		}
		
        public void testTerminateButtonCallback() throws Exception {
            button.doClick();
            assertEquals(1, terminations);
		}
		
		// implement EmployeeTerminatorController
        public void selectionChanged(String employee) {
            selectedValue = employee;
            selectionCount++;
		}
		
		public void terminate() {
            terminations++;
		}
	}