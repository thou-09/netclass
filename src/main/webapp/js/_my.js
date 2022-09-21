// 项目访问路径 pageContext.request.ContextPath
window.baseUrl="http://localhost:8080/netclass";
// 上传文件访问路径
window.fileUrl="http://localhost:9090";

// 格式化时间，暂不使用，换成 java 实体类中的 JsonField() 注解
function dateFormat(d) {
    if (d) {
        let date = new Date(d);
        let year = date.getFullYear();
        let month = date.getMonth() + 1 < 10 ? '0'+(date.getMonth() + 1) : date.getMonth() + 1;
        let day = date.getDate() < 10 ? '0'+date.getDate() : date.getDate();
        let hour = date.getHours() < 10 ? '0'+date.getHours() : date.getHours();
        let minute = date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes();
        let second = date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds();
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    } else {
        return '----'
    }
}

// input date 内容改变事件
$('input[type=date]').change((e) => {
    let date = $(e.target).val();
    if (date) {
        $(e.target).addClass("selected");
    } else {
        $(e.target).removeClass("selected");
    }
});

// 自定义序列化 form 表单方法
function _serialize(e) {
    let str = '';
    let inputs = e.find('input');
    for (let i = 0; i < inputs.length; i++) {
        let item = $(inputs[i]);
        if (!item.attr('disabled')) {
            if (item.val()) {
                if (item.attr('type') === 'text') {
                    str += item.attr('name') + '=' + item.val() + '&';
                }
                if (item.attr('type') === 'hidden') {
                    str += item.attr('name') + '=' + item.val() + '&';
                }
                if (item.attr('type') === 'date') {
                    str += item.attr('name') + '=' + (item.val() + ' 00:00:00') + '&';
                }
            }
        }
    }
    let selects = e.find('select');
    for (let i = 0; i < selects.length; i++) {
        let select = $(selects[i]);
        if (select.val()) {
            str += select.attr('name') + '=' + select.val() + '&';
        }
    }
    if (str.length !== 0) {
        str = str.substring(0, str.length - 1);
    }
    return str;
}

// 自定义栈
class Stack {
    constructor() {
        this.stack = []
    }

    // 入栈
    push(element) {
        this.stack.push(element)
    }

    // 出栈
    pop() {
        return this.stack.pop();
    }

    // 栈长度
    size() {
        return this.stack.length;
    }

    // 是否栈空
    isEmpty() {
        return this.size() === 0;
    }

    // 查看栈顶元素
    peek() {
        return this.stack[this.size() - 1];
    }

    // 替换栈顶元素
    replace(element) {
        this.stack.pop();
        this.stack.push(element)
    }

    // 查看栈中全部元素
    toString() {
        return this.stack.join('|');
    }

    // 清空栈
    clear() {
        this.stack = [];
    }
}

// AjaxParam 封装发送 ajax 请求的 url 和 data
class AjaxParam {
    constructor(url, data) {
        this.url = url;
        this.data = data;
    }

    toString() {
        return 'url:' + this.url + ',data:' + this.data;
    }
}