using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfClient
{
    class Controller
    {
        /// <summary>
        /// List of classInformation
        /// </summary>
        public List<ClassInformation> list;

        /// <summary>
        /// File to read from the given file 
        /// </summary>
        string[] lines;


        /// <summary>
        /// Default Constructor
        /// </summary>
        public Controller()
        {
            list = new List<ClassInformation>();

            lines = System.IO.File.ReadAllLines("CrossDomain.txt");
        }

        /// <summary>
        /// Returns list of class information from service
        /// </summary>
        /// <returns></returns>
        public List<ClassInformation> getClassInformation()
        {
            int index = 0;
            while (index < lines.Length)
            {
                ClassInformation cls = new ClassInformation();
                cls.classFormat = lines[index];
                cls.classNumber = lines[index + 1];
                cls.coursenumber = lines[index + 2];
                cls.course = lines[index + 3];
                cls.days = lines[index + 4];
                cls.endTime = lines[index + 5];
                cls.instructor = lines[index + 6];
                cls.startTime = lines[index + 7];

                index = index + 8;

                list.Add(cls);
            }

            return list;

        }
    }
}


