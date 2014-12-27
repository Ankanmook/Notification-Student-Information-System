using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using WpfClient.ServiceReference;

namespace WpfClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        /// <summary>
        /// Data Access Layer Variable
        /// </summary>
        DataAcessLayer dl;

        /// <summary>
        /// List of Class Information
        /// </summary>
        List<ClassInformation> stdList;

        /// <summary>
        /// Subscription Response
        /// </summary>
        subscriptionResponse resp;

        /// <summary>
        /// Default Constructor
        /// </summary>
        public MainWindow()
        {
            InitializeComponent();
            dl = new DataAcessLayer();

            btnSubscribe.IsEnabled = false;
            btnSubscribe.Visibility = System.Windows.Visibility.Hidden;

            grdClassSchedule.Visibility = System.Windows.Visibility.Hidden;

            resp = new subscriptionResponse();
        }


        /// <summary>
        /// Unused Event
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void grdClassSchedule_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

 

        /// <summary>
        /// Subscribe all the student
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSubscribe_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                if (stdList != null)
                {
                    foreach (var v in stdList)
                    {
                        if (v.isSubscribed == true && v.classNumber != null)
                        {
                            subscribeRequest sub1 = new subscribeRequest();
                            subscribe2Request sub2 = new subscribe2Request();

                            sub2.cno = v.classNumber;
                            sub2.uid = txtUid.Text;
                            resp = dl.client.subscribe2(txtUid.Text, v.classNumber);

                            MessageBox.Show("Congratulation, You have been successfully subscribed for the class " + v.course +" with Professor " + v.instructor, "Success");         
                        }
                    }

                }

                else
                {
                    MessageBox.Show("There is no information to be subscribed to", "Error");
                }

            }

            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error");
            }


            txtFirstName.Clear();
            txtLastName.Clear();
            txtMajor.Clear();
            txtUid.Clear();
            txtPhone.Clear();
            txtEmail.Clear();
            stdList.Clear();

            btnSubscribe.Visibility = System.Windows.Visibility.Hidden;
            btnSubscribe.Visibility = System.Windows.Visibility.Hidden;

            mainWindow.Height = 320;

        }

        /// <summary>
        /// Button Get Student Profile
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnGetStudentProfile_Click(object sender, RoutedEventArgs e)
        {
            //Test Value
            //990009592

            try
            {
                student std = dl.getStudentProfileData(txtUid.Text);

                txtFirstName.Text = std.firstname;
                txtLastName.Text = std.lastname;
                txtMajor.Text = std.major;
                txtUid.Text = std.uid;
                txtPhone.Text = std.phone;
                txtEmail.Text = std.email;
                
                // Adding class information 
                stdList =dl.getStudentClassInformation(txtUid.Text);

                grdClassSchedule.ItemsSource = stdList;

                bindDataGrid();


                btnSubscribe.IsEnabled = true;
                btnSubscribe.Visibility = System.Windows.Visibility.Visible;

                mainWindow.Height = 700;
                grdClassSchedule.Visibility = System.Windows.Visibility.Visible;
            }
            catch (Exception ex)
            {
                if (stdList == null)
                {
                    MessageBox.Show("Student Has Not Enrolled in Any Course", "Enrollement Error");

                }

                else
                {
                    MessageBox.Show(ex.Message, "Error");
                    stdList = null;
                }


                txtFirstName.Clear();
                txtLastName.Clear();
                txtMajor.Clear();
                txtUid.Clear();
                txtPhone.Clear();
                txtEmail.Clear();
                //stdList.Clear();
            }
        }

        /// <summary>
        /// Binds the data grid
        /// </summary>
        public void bindDataGrid()
        {
            DataGridTextColumn col1 = new DataGridTextColumn();
            col1.Binding = new Binding("classNumber");
            col1.Header = "Class Number";
            col1.IsReadOnly = true;

            DataGridTextColumn col2 = new DataGridTextColumn();
            col2.Binding = new Binding("classFormat");
            col2.Header = "Class Format";
            col2.IsReadOnly = true;

            DataGridTextColumn col3 = new DataGridTextColumn();
            col3.Binding = new Binding("coursenumber");
            col3.Header = "Course Number";
            col3.IsReadOnly = true;

            DataGridTextColumn col4 = new DataGridTextColumn();
            col4.Binding = new Binding("course");
            col4.Header = "Course";
            col4.IsReadOnly = true;

            DataGridTextColumn col5 = new DataGridTextColumn();
            col5.Binding = new Binding("days");
            col5.Header = "Days";
            col5.IsReadOnly = true;
            
            DataGridTextColumn col6 = new DataGridTextColumn();
            col6.Binding = new Binding("startTime");
            col6.Header = "Start Time";
            col6.IsReadOnly = true;
            
            DataGridTextColumn col7 = new DataGridTextColumn();
            col7.Binding = new Binding("endTim");
            col7.Header = "eEnd Time";
            col7.IsReadOnly = true;

            DataGridTextColumn col8 = new DataGridTextColumn();
            col8.Binding = new Binding("instructor");
            col8.Header = "Instructor";
            col8.IsReadOnly = true;

            CustomDataGridCheckBoxColumn col9 = new CustomDataGridCheckBoxColumn();
            col9.Binding = new Binding("isSubscribed");
            col9.Header = "Subscribed";
            

            grdClassSchedule.Columns.Add(col9);
            grdClassSchedule.Columns.Add(col1);
            grdClassSchedule.Columns.Add(col2);
            grdClassSchedule.Columns.Add(col3);
            grdClassSchedule.Columns.Add(col4);
            grdClassSchedule.Columns.Add(col5);
            grdClassSchedule.Columns.Add(col6);
            grdClassSchedule.Columns.Add(col7);
            grdClassSchedule.Columns.Add(col8);

        }

            }

    /// <summary>
    /// Custom CustomDataGridCheckBoxColumn Class 
    /// wih an new even handler 
    /// </summary>
    public class CustomDataGridCheckBoxColumn : DataGridCheckBoxColumn
    {
        /// <summary>
        /// General Edit element event
        /// </summary>
        /// <param name="cell">DataGrid Check Box cell</param>
        /// <param name="dataItem">data item</param>
        /// <returns>check box</returns>
        protected override FrameworkElement GenerateEditingElement(DataGridCell cell, object dataItem)
        {

            CheckBox checkBox = base.GenerateEditingElement(cell, dataItem) as CheckBox;
            checkBox.Checked += new RoutedEventHandler(HandleClick);
            checkBox.Unchecked += new RoutedEventHandler(HandleClick);
            return checkBox;
        }

        /// <summary>
        /// Handle Click Event 
        /// Just changes the boolean
        /// value of the element in the text box
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public void HandleClick(object sender, RoutedEventArgs e)
        {
            
        }
    }
}























/* 
 * End of code Test Case files
//990009592 is not subscribed to 23456, 34567, 45678

//990009592 is not subscribed to 23456, 34567, 45678
//subscribed for any course
*/