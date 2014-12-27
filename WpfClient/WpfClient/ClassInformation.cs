using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfClient
{
    /// <summary>
    /// Gives a custom class for 
    /// class information
    /// </summary>
    class ClassInformation
    {
        /// <summary>
        /// Class Information Fields
        /// with Getter and Setter 
        /// </summary>
        
        public string classNumber { set; get; }
        public string classFormat { set; get; }
        public string coursenumber { set; get; }
        public string course { set; get; }
        public string days { set; get; }
        public string startTime { set; get; }
        public string endTime { set; get; }
        public string instructor { set; get; }
        public bool isSubscribed { set; get; }
    }
}
