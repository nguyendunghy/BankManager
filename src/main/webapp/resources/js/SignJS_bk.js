/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var socket = {
    url: "",
    token: "Token",
    dataSign: "",
    serialCert: "",
    sign: function (/*Function*/receiveFunc, /*Function*/errorFunc) {
        var sign = {"token": this.token, "dataSign": this.dataSign, "serialToCheck": this.serialCert};
        var signString = JSON.stringify(sign);
        var ws = new WebSocket(this.url);
        ws.onopen = function () {
            ws.send(signString);
        };
        ws.onmessage = function (evt) {
            receiveFunc(evt);
        };
        ws.onclose = function (evt) {
            errorFunc(evt);            
        };
    }
};