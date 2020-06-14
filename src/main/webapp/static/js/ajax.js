document.write("<script type='text/javascript' src='/static/js/qs.js'></script>");
document.write("<script type='text/javascript' src='/static/js/axios-0.18.0.js'></script>");
const basePath = 'http://localhost:8081'

function ajaxPost(url, data, token, callback) {
  $.ajax({
    url: basePath + url,
    type: 'post',
    dataType: 'json',
    contentType: 'application/json;charset=utf-8',
    data: JSON.stringify(data),
    headers: {
      "token": token
    },
    success: function (result) {
      callback(result)
    },
    error: function (result) {
      console.log(result)
    }
  });
}

function ajaxGet(url, data, token, callback) {
  const param = Qs.stringify(data)
  $.ajax({
    url: basePath + url + '?' + param,
    type: 'get',
    headers: {
      "token": token
    },
    success: function (result) {
      callback(result)
    },
    error: function (result) {
      console.log(result)
    }
  });
}


function vue(e1, e2){
  const model = new Object(null)
  e1.addEventListener('change',() => {model.view = e1.value})
  Object.defineProperty(model,'view',{
    set:function (newValue) {
      view = newValue
      e2.value = view
    },
    get:function () {
      return view
    }
  })
}
