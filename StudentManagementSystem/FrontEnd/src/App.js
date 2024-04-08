import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import StudentsView from './Components/student/StudentsView';
import Home from './Home';
import NavBar from './Components/common/Navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddStudent from './Components/student/AddStudent';
import EditStudent from './Components/student/EditStudent';
import StudentPofile from './Components/student/StudentProfile';

function App() {
  return (
    <main className="container mt-5">
      <Router>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/view-students" element={<StudentsView />} />
          <Route path="/add-student" element={<AddStudent />} />
          <Route path="/edit-student/:id" element={<EditStudent/>} />
          <Route path="/student-profile/:id" element={<StudentPofile/>} />
        </Routes>
      </Router>
    </main>
  );
}

export default App;
