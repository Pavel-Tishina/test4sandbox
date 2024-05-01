// main page
import React from "react";
import EmployeeService from "../services/EmployeeService";
import Pagination from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Form from 'react-bootstrap/Form';
import EmployeeElementComponent from './EmployeeElementComponent'

import { isObjectLiteralElementLike } from "typescript";

class EmployeeComponent extends React.Component {
    

    constructor(props) {
        super(props)
        this.state = {
            activePage      : props.activePage || undefined,
            totalN          : undefined,
            limitN          : 5,
            employees       : [],
            dialogType      : undefined,
            showDialog      : false,
            employees2Del   : [],            
            selectedEmp     : undefined,
            selectedSupEmp  : undefined
        }

        this.setActivePage = this.setActivePage.bind(this);
        this.prePageN = this.prePageN.bind(this);
        this.nxtPageN = this.nxtPageN.bind(this);
        this.maxPage = this.maxPage.bind(this);

        this.undefSelectedEmployeeData = this.undefSelectedEmployeeData.bind(this);
        this.getSelectedEmployeeData = this.getSelectedEmployeeData.bind(this);
    }

    componentDidMount() {
        // get total elements count, and 
        if (this.state.totalN === undefined || this.state.activePage === undefined) {
            EmployeeService.getTotal().then((response) => {
                console.dir(response.data.total);
                this.setState({ totalN: response.data.total });
                this.setState({ activePage: Math.floor(response.data.total / this.state.limitN / 2) });
                console.log(this.state.totalN + "\t" + this.state.activePage);
            }, (error) => {
                console.log(error);
                this.setState({ totalN: 0 });
                this.setState({ activePage: 0 });
            });
        }
        
        const p = this.state.activePage !== undefined && this.state.activePage >= 0
            ? this.state.activePage
            : 0;


        EmployeeService.getEmployeePage(p, this.state.limitN).then((response) => {
            console.log(response.data);
            this.setState({ employees: response.data.result})
          }, (error) => {
            console.log(error);
            this.setState({ employees: []})
          });

    }

    prePageN() {
        return this.state.activePage - 1;
    }

    nxtPageN() {
        return this.state.activePage + 1;
    }

    maxPage() {
        return Math.floor(this.state.totalN / this.state.limitN);
    }

    setActivePage = (n) => {
        if (n < 0) {
            this.setState({ activePage : 0})
        }
        else if (n > this.maxPage()) {
            this.setState({ activePage : this.maxPage() })
        }
        else {
            this.setState({ activePage : n})
        }    
    }

    getSelectedEmployeeData = (id) => {
        this.undefSelectedEmployeeData();

        const selectedEmp = this.state.employees.find(e => e.id === id);
        if (selectedEmp !== null || selectedEmp !== undefined) {
            this.setState({ selectedEmp : selectedEmp })

            const sup = selectedEmp.supervisor;
            if (sup !== null && sup !== undefined && sup > 0) {
                
                EmployeeService.getEmployeeById(sup).then((response) => {
                    console.log(response.data);
                    this.setState({ selectedSupEmp: response.data.result});
                  }, (error) => {
                    console.log(error);
                    this.setState({ selectedSupEmp: undefined});
                  });
            }
        }
        
    }

    undefSelectedEmployeeData() {
        this.setState({
            employees2Del   : [],
            selectedEmp     : undefined,
            selectedSupEmp  : undefined
        });
    }

    // TODO
    // dialog show and hide!

    render (){
        const { activePage, totalN, limitN, employees, showDialog, dialogType,
            selectedEmp, selectedSupEmp
        } = this.state;


        return (
            <>
                {employees.map(emp => (
                    <EmployeeElementComponent 
                        id={emp.id} 
                        firstName={emp.firstName}
                        lastName={emp.lastName} 
                        position={emp.position}
                        supId={emp.supId}
                    />
                ))}

                <div style={{ display: 'flex', justifyContent: 'space-between', width: '40rem' }}>
                    <Pagination>
                        <Pagination.First onClick={() => this.setActivePage(0)} />
                        <Pagination.Prev onClick={() => this.setActivePage(activePage - 1)}/>
                        
                        <Pagination.Item active>{activePage}</Pagination.Item>
                    
                        <Pagination.Next onClick={() => this.setActivePage(activePage + 1)} />
                        <Pagination.Last onClick={() => this.setActivePage(this.maxPage())} />
                    </Pagination>

                    <Button variant="primary" >New</Button>
                    <Button variant="secondary" >Del</Button>    
                </div>




                <Modal show={showDialog} onHide={this.closeDialog} className="overlay">
                    <Modal.Header closeButton>
                        <Modal.Title>
                            {dialogType == 'supervisor' && (
                                <p>Supervisor of
                                <p>{selectedEmp.firstName} {selectedEmp.lastName}</p></p>
                            )}
                            {dialogType == 'employee' && (
                                <p>Information about Employye</p>
                            )}
                            {dialogType == 'add' && (
                                <p>Add new Employee</p>
                            )}
                            {dialogType == 'del' && (
                                <p>Delete selected Employees</p>
                            )}
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        {dialogType === 'del' && (
                            <p>Do you want delete users?</p>
                        )}


                        {dialogType !== 'add' && dialogType !== 'del' && (
                            <div>
                                <Form.Label>ID</Form.Label>
                                <Form.Control value={selectedEmp.id} readOnly />
                                <Form.Text id="field-id" />
                            </div>  
                        )}
                        {dialogType !== 'del' && (
                            <div>
                                <div>
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control value={selectedEmp.firstName} 
                                        onChange={(e) => this.setFieldValue(e, 'firstName')}
                                    />
                                    <Form.Text id="field-first-name" />
                                </div>
                                <div>
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control value={selectedEmp.lastName} 
                                        onChange={(e) => this.setFieldValue(e, 'lastName')}
                                    />
                                    <Form.Text id="field-last-name" />
                                </div>
                                <div>
                                    <Form.Label>Position</Form.Label>
                                    <Form.Control value={selectedEmp.position} 
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
                            </div>
                        )}    
                        {dialogType !== 'add' && dialogType !== 'del' && (
                            <div>
                                <Form.Label>Created</Form.Label>
                                <Form.Control value={selectedEmp.created} readOnly/>
                                <Form.Text id="field-created" />
                            </div>  
                        )}
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.closeDialog}>
                            Close
                        </Button>
                        {dialogType === 'add' || dialogType === 'employee' && (
                            <Button variant="primary" onClick={this.closeDialog}>
                                Save Changes
                            </Button>
                        )}
                        {dialogType === 'del' && (
                            <Button variant="primary" onClick={this.closeDialog}>
                                Remove
                            </Button>
                        )}
                    </Modal.Footer>
                </Modal>
            
            </>
        )
    }
}

export default EmployeeComponent



/*
            <>
            <Pagination>
                <Pagination.First onClick={() =>this.setActivePage(0)} />
                <Pagination.Prev onClick={() => this.setActivePage(activePage - 1)}/>
                {activePage - 2 > 0 &&(
                    <Pagination.Ellipsis />    
                )}
                {this.prePageN() >= 0 &&(
                    <Pagination.Item>{this.prePageN()}</Pagination.Item>
                )}
                <Pagination.Item active>{activePage}</Pagination.Item>
                {this.nxtPageN() <= this.maxPage() &&(
                    <Pagination.Item>{this.nxtPageN()}</Pagination.Item>
                )}
                {activePage + 2 < this.maxPage() &&(
                    <Pagination.Ellipsis />    
                )}
            
                <Pagination.Next onClick={() => this.setActivePage(activePage + 1)} />
                <Pagination.Last onClick={() => this.setActivePage(this.maxPage())} />
            </Pagination>
            </>
            */