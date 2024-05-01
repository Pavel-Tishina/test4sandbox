// dialog for show data of employee and supervisor, also for add new employee
import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';

import EmployeeService from "../services/EmployeeService";

import './styles.css';

//class CommonDialog extends React.Component {
function CommonDialog() {
  const [showDialog, setShow] = useState(false);
  const [id, setId] = useState();
  let firstName;
  let lastName;
  let position;
  let supId;
  let supFirstName;
  let supLastName;
  let created;
  let dialogType;
    

  const handleClose = () => setShow(false);
   const handleShow = () => setShow(true);

    const setFieldValue = (event, fieldName) => {
      if (fieldName === 'firstName') {
        this.setState({firstName : event.target.value});
      } else if (fieldName === 'lastName') {
        this.setState({lastName : event.target.value});
      } else if (fieldName === 'position') {
        this.setState({position : event.target.value});
      }
    };

    function getSupFullName() {
      return supId > 0 
              ? supFirstName + ' ' + supLastName + '(' + supId + ')'
              : '';
    }

    /*
    componentDidMount() {
      console.log('DATA: '
        + "\n\t" + this.state.dialogType
        + "\n\t" + this.state.id
        + "\n\t" + this.state.supId
      );

      if (this.state.dialogType !== 'add') {
        // get current by id
        EmployeeService.getEmployeeById(
          this.state.dialogType === 'employee'
            ? this.state.id : this.state.supId).then((response) => {
          console.log(response.data);
          
          //this.setState({ employees: response.json})
        }, (error) => {
          console.log(error);
        });

        // get a supervisor
      }

    
  }
  */



        return(
          <>
            <div
            className="modal show"
            style={{ display: 'block', position: 'initial', textAlign: 'left' }}
          >
          
          <Modal.Dialog show={showDialog} backdrop="static" keyboard={false} onHide={this.handleClose} >
              <Modal.Title >
                  {dialogType == 'supervisor' && (
                    <p>Supervisor of
                    <p>{firstName} {lastName}</p></p>
                  )}
                  {dialogType == 'employee' && (
                    <p>Information about Employye</p>
                  )}
                  {dialogType == 'add' && (
                    <p>Add new Employee</p>
                  )}
                  {dialogType == 'del' && (
                    <p>Add new Employee</p>
                  )}
              </Modal.Title>
              <Modal.Body >
                {dialogType !== 'add' && dialogType !== 'rem' && (
                  <div>
                    <Form.Label>ID</Form.Label>
                    <Form.Control value={id} readOnly />
                    <Form.Text id="field-id" />
                  </div>  
                )}

                <div>
                  <Form.Label>First Name</Form.Label>
                  <Form.Control value={firstName} 
                    onChange={(e) => this.setFieldValue(e, 'firstName')}
                  />
                  <Form.Text id="field-first-name" />
                </div>
                <div>
                  <Form.Label>Last Name</Form.Label>
                  <Form.Control value={lastName} 
                    onChange={(e) => this.setFieldValue(e, 'lastName')}
                  />
                  <Form.Text id="field-last-name" />
                </div>
                <div>
                  <Form.Label>Position</Form.Label>
                  <Form.Control value={position} 
                    onChange={(e) => this.setFieldValue(e, 'position')}
                  />
                  <Form.Text id="field-position" />
                </div>
                <div>
                  <Form.Label>Supervisor</Form.Label>
                  <Form.Control value={this.getSupFullName()} 

                  />
                  <Form.Text id="field-supervisor" />
                </div>

                {dialogType !== 'add' && (
                  <div>
                    <Form.Label>Created</Form.Label>
                    <Form.Control value={created} readOnly/>
                    <Form.Text id="field-created" />
                  </div>  
                )}
              </Modal.Body>
      
              <Modal.Footer>
                <Button variant="secondary" onClick={this.handleClose}>Close</Button>
                {dialogType != 'supervisor' && (
                    <Button variant="primary">Save changes</Button>
                )}    
              </Modal.Footer>
            </Modal.Dialog>
          </div>
          </>
        );
    }


export default CommonDialog;