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
    <script src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
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
            <li class="nav-item"><a href="/" class="nav-link">员工相似度分析</a></li>
            <li class="nav-item"><a class="nav-link active">员工日常分析</a></li>
            <li class="nav-item"><a href="/employee" class="nav-link">员工信息分析</a></li>
            <li class="nav-item"><a href="/logout" class="nav-link">退出</a></li>
        </ul>
    </header>

    <div class="p-5 mb-4 bg-light rounded-3">
        <div class="container-fluid py-1">
            <label class="form-label">员工使用频率分析</label>
            <div class="input-group" style="max-width: 420px">
                <label>
                    <input v-model="employeeId" type="text" class="form-control" placeholder="请输入员工号，例如： 0">
                </label>
                <input type="date" class="form-control" style="width: 124px; margin-left: 16px" v-model="date">
                <button @click="queryDailyUseFrequency" class="btn btn-secondary">检索</button>
            </div>

            <div id="dailyFrequency" class="mt-4" style="height: 500px"></div>

            <div id="dailyLongestUsage" class="mt-4" style="height: 500px"></div>

        </div>
    </div>

    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        </ul>
        <p class="text-center text-muted">兴业证券实习-郑西坤</p>
    </footer>
</div>

<script>
    function second(value) {

        let theTime = parseInt(value);// 秒
        let middle = 0;// 分
        let hour = 0;// 小时

        if (theTime >= 60) {
            middle = parseInt(theTime / 60);
            theTime = parseInt(theTime % 60);
            if (middle >= 60) {
                hour = parseInt(middle / 60);
                middle = parseInt(middle % 60);
            }
        }
        let result = "" + parseInt(theTime) + "秒";
        if (middle > 0) {
            result = "" + parseInt(middle) + "分" + result;
        }
        if (hour > 0) {
            result = "" + parseInt(hour) + "小时" + result;
        }
        return result;
    }

    const {createApp} = Vue
    const app = createApp({
        data() {
            return {
                employeeId: "",
                axiosInstance: axios.create({
                    baseURL: 'http://127.0.0.1:8080',
                    timeout: 5000
                }),
                date: ''
            }
        },
        methods: {
            queryDailyUseFrequency() {
                this.axiosInstance.get('/employees/daily', {
                    params: {
                        employeeId: this.employeeId,
                        date: this.date
                    }
                }).then(response => {
                    let data = response.data
                    let tmp = [0, 0, 0, 0, 0, 0, 0, 0, 0]
                    for (let item of data) {
                        console.log(item)
                        hour = item['createTime'].split(":")[0]
                        tmp[hour - 9] = item['frequency']
                    }
                    let myChart = echarts.init(document.getElementById('dailyFrequency'));
                    option = {
                        xAxis: {
                            data: ['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00']
                        },
                        yAxis: {},
                        series: [
                            {
                                data: tmp,
                                type: 'bar',
                                itemStyle: {
                                    borderWidth: 1,
                                    borderType: 'solid',
                                    borderColor: '#73c0de',
                                }
                            }
                        ]
                    };
                    myChart.setOption(option);
                })
                this.axiosInstance.get('/employees/daily/longest', {
                    params: {
                        employeeId: this.employeeId,
                        date: this.date
                    }
                }).then(response => {
                    let data = response.data
                    console.log(data)
                    let keys = []
                    let values = []
                    for (let obj of data) {
                        keys = keys.concat(Object.keys(obj))
                        values = values.concat(Object.values(obj)[0] / 1000)
                    }

                    option = {
                        xAxis: {
                            max: 'dataMax'
                        },
                        yAxis: {
                            type: 'category',
                            data: keys,
                            inverse: true,
                            animationDuration: 300,
                            animationDurationUpdate: 300,
                            max: 5 // only the largest 3 bars will be displayed
                        },
                        series: [
                            {
                                realtimeSort: true,
                                name: '使用时长',
                                type: 'bar',
                                data: values,
                                label: {
                                    show: true,
                                    position: 'right',
                                    valueAnimation: true,
                                    formatter: (params) => {
                                        return [
                                            second(params.value)
                                        ]
                                    },
                                },
                                itemStyle: {
                                    borderWidth: 1,
                                    borderType: 'solid',
                                    borderColor: '#73c0de',
                                }
                            }
                        ],
                        legend: {
                            show: true
                        },
                        animationDuration: 1000,
                        animationDurationUpdate: 1000,
                        animationEasing: 'linear',
                        animationEasingUpdate: 'linear'
                    };
                    let myChart = echarts.init(document.getElementById('dailyLongestUsage'));
                    myChart.setOption(option);
                })
            }
        }
    })
    app.mount("#app");
</script>
</body>
</html>