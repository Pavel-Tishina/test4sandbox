import axios from 'axios';

const EMPLOYEE_REST_API_URL = 'http://localhost:8080/rest/api/employee';

class EmployeeService {

    getEmployeeById(id) {
        return axios.get(EMPLOYEE_REST_API_URL + '?id=' + id, {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET,PUT,PUSH,DELETE',
                'Accept': '*/*',
                'Referer': "http://localhost:3000"
            }
        });
    }

    /*
    //TODO
    putEmployeeById() {
        return axios.put(EMPLOYEE_REST_API_URL);
    }

    //TODO
    postEmployeeById() {
        return axios.post(EMPLOYEE_REST_API_URL);
    }
    */

    delEmployeeById(id) {
        return axios.del(EMPLOYEE_REST_API_URL + '?id=' + id);
    }

    /*
    //TODO
    getEmployeeList() {
        return axios.get(EMPLOYEE_REST_API_URL + '/list');
    }
    */
//'Access-Control-Allow-Origin'
    getEmployeePage(p, lim) {
        return axios.get(EMPLOYEE_REST_API_URL + '/page?p=' + p + '&lim=' + lim, {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET,PUT,PUSH,DELETE',
                'Accept': '*/*',
                'Referer': "http://localhost:3000"
            }
        });
    }

    getTotal() {
        return axios.get(EMPLOYEE_REST_API_URL + '/total', {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET,PUT,PUSH,DELETE',
                'Accept': '*/*',
                'Referer': "http://localhost:3000"
            }
        });
    }


}

export default new EmployeeService();