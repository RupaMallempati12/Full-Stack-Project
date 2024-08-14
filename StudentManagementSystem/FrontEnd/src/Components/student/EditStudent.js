import { useNavigate,Link, useParams } from 'react-router-dom';
import {useState, useEffect} from 'react';
import axios from 'axios';

function EditStudent() {
    let navigate = useNavigate();

    const [student,setStudent] = useState({
        id : '',
        firstName : '',
        lastName : '',
        email : '',
        department : ''
    });

    const { firstName, lastName, email, department } = student;

    const { id } = useParams();

    useEffect(()=> {
        loadStudents();
       },[id])
   
       const loadStudents = async() => {
        const result = await axios.get(`http://localhost:8080/students/student/${id}`);
        setStudent(result.data);
       }

    const handleInputChange = (e) => {
      e.preventDefault();
      setStudent({...student, [e.target.name] : e.target.value});
    }

    const updateStudent = async (e) => {
        e.preventDefault();
        try {
            await axios.patch(
                `http://localhost:8080/students/update/${id}`,
             student);
        } catch (error) {
            console.error("Error:", error);
        }
        navigate("/view-students");
    }
    
    return (
    <div className="col-sm-8 py-2 px-5">  
      <form>

      <div className="input-group mb-5">
            <label 
            className="input-group-text"
            htmlFor="firstName"
            >Id
            </label>
            <input
            className="form-control col-sm-6"
            type='number'
            name="id"
            id="id"
            required
            value={student.id}
            onChange={(e)=> handleInputChange(e)}
            />
        </div>

        <div className="input-group mb-5">
            <label 
            className="input-group-text"
            htmlFor="firstName"
            >First Name
            </label>
            <input
            className="form-control col-sm-6"
            type="text"
            name="firstName"
            id="firstName"
            required
            value={firstName}
            onChange={(e)=> handleInputChange(e)}
            />
        </div>

        <div className="input-group mb-5">
            <label 
            className="input-group-text"
            htmlFor="lastName"
            >Last Name
            </label>
            <input
            className="form-control col-sm-6"
            type="text"
            name="lastName"
            id="lastName"
            required
            value={lastName}
            onChange={(e)=> handleInputChange(e)}
            />
        </div>

        <div className="input-group mb-5">
            <label 
            className="input-group-text"
            htmlFor="email"
            >Email
            </label>
            <input
            className="form-control col-sm-6"
            type="text"
            name="email"
            id="email"
            required
            value={email}
            onChange={(e)=> handleInputChange(e)}
            />
        </div>

        <div className="input-group mb-5">
            <label 
            className="input-group-text"
            htmlFor="firstName"
            >Department
            </label>
            <input
            className="form-control col-sm-6"
            type="text"
            name="department"
            id="department"
            required
            value={department}
            onChange={(e)=> handleInputChange(e)}
            />
        </div>

        <div className="row mb-5">
					<div className="col-sm-2">
						<button
							type="submit"
							className="btn btn-outline-success btn-lg"
                            onClick={updateStudent}
                            >
							Save
						</button>
					</div>

					<div className="col-sm-2">
						<Link
							to={"/view-students"}
							type="submit"
							className="btn btn-outline-warning btn-lg">
							Cancel
						</Link>
					</div>
				</div>

      </form>
    </div>
  );
}

export default EditStudent;
