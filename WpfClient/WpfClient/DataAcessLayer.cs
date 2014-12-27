using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using WpfClient.ServiceReference;

namespace WpfClient
{
    class DataAcessLayer
    {
        /// <summary>
        /// The Web service client
        /// </summary>
        public StudentInformationServiceClient client;

        /// <summary>
        /// Controller
        /// </summary>
        Controller ctrl;

        /// <summary>
        /// Default Constructor
        /// </summary>
        public DataAcessLayer()
        {
            client = new StudentInformationServiceClient();
            ctrl = new Controller();
        }

        /// <summary>
        /// Gets the students profile for a university id
        /// </summary>
        /// <param name="uid"></param>
        /// <returns></returns>
        public student getStudentProfileData(string uid)
        {
            return client.getStudentProfile(uid);
        }


        /// <summary>
        /// Gets class information for a university id
        /// </summary>
        /// <param name="Uid"></param>
        /// <returns></returns>
        public List<ClassInformation> getStudentClassInformation(string Uid)
        {
            List<ClassInformation> lstClass = new List<ClassInformation>();
            classSchedule[] cls = client.getStudentEnrollement(Uid);

            classSchedule[] entire = client.getClassSchedules();

            foreach (var c in cls)
            {
                ClassInformation skdl = getClassInfo(c.classNumber);
                lstClass.Add(skdl);
            }

            return lstClass;
        }


        /// <summary>
        /// Returns entire class information for the given class number
        /// </summary>
        /// <param name="ClassNumber"></param>
        /// <returns></returns>
        public ClassInformation getClassInfo(string ClassNumber)
        {
             List<ClassInformation> lstClass = ctrl.getClassInformation();

             ClassInformation crl = (from classData in lstClass
                                     where classData.classNumber.Contains(ClassNumber)
                                     select classData).FirstOrDefault();
             return crl;
        }


    }

}
