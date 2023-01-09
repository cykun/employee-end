<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>员工端数据分析系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/vue@3"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="container" id="app">
    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32">
                <use xlink:href="#bootstrap"></use>
            </svg>
            <span class="fs-4">员工端数据分析</span>
        </a>

        <ul class="nav nav-pills">
            <li class="nav-item"><a class="nav-link active">员工相似度分析</a></li>
            <li class="nav-item"><a href="/daily" class="nav-link">员工日常分析</a></li>
            <li class="nav-item"><a href="/employee" class="nav-link">员工信息分析</a></li>
            <li class="nav-item"><a href="/logout" class="nav-link">退出</a></li>
        </ul>
    </header>

    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-1">
            <label class="form-label">员工相似检索</label>
            <div class="input-group" style="max-width: 360px">
                <label>
                    <input v-model="employeeId" @keyup.enter.native="queryEmployeesByFunctions" type="text"
                           class="form-control" placeholder="请输入员工号，例如： 0">
                </label>
                <button class="btn btn-secondary" @click="querySimilarEmployees">检索</button>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>员工号</th>
                    <th>员工名</th>
                    <th>相似度（%）</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in employeeSimilarityList">
                    <td>{{item.employeeId}}</td>
                    <td>{{item.name}}</td>
                    <td>{{parseFloat(item.similarity * 100).toFixed(2)}} %</td>
                </tr>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
    </div>

    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-1">
            <label class="form-label">功能点列表</label>
            <div class="input-group" style="max-width: 360px">
                <label>
                    <input v-model="functions" @keyup.enter.native="querySimilarEmployees" type="text"
                           class="form-control" placeholder="请输入功能点列表，例如：1,2,3">
                </label>
                <button @click="queryEmployeesByFunctions" class="btn btn-secondary">检索</button>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>员工号</th>
                    <th>员工名</th>
                    <th>相似度（%）</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="item in employeesList">
                    <td>{{item.employeeId}}</td>
                    <td>{{item.name}}</td>
                    <td>{{parseFloat(item.similarity * 100).toFixed(2)}} %</td>
                </tr>
                </tbody>
                <tfoot>
                </tfoot>
            </table>
        </div>
    </div>

    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        </ul>
        <p class="text-center text-muted">兴业证券实习-郑西坤</p>
    </footer>
</div>
<script>
    const {createApp} = Vue
    const app = createApp({
        data() {
            return {
                employeeId: "",
                functions: "",
                employeeSimilarityList: [],
                employeesList: [],
                axiosInstance: axios.create({
                    baseURL: 'http://127.0.0.1:8080',
                    timeout: 5000
                })
            }
        },
        methods: {
            querySimilarEmployees() {
                this.axiosInstance.get('/employees/similar/' + this.employeeId)
                    .then(response => {
                        this.employeeSimilarityList = response.data
                    })
            },
            queryEmployeesByFunctions() {
                this.axiosInstance.get('/employees/functions?functions=' + this.functions)
                    .then(response => {
                        this.employeesList = response.data
                    }).catch(error => {
                    if (error.response) {
                        alert("出现了错误")
                    }
                })
            }
        }
    })
    app.mount('#app')
</script>
</body>
</html>