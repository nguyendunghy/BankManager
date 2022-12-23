/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var socket = {
//    url: "ws://localhost:8004/",
    url: "wss://localhost:8006/",
    token: "Token",
    dataSign: "",
    serialCert: "",
    messageCode: "",
    sign: function (/*Function*/receiveFunc, /*Function*/errorFunc) {
        var sign = {"token": this.token, "dataSign": this.dataSign, "serialToCheck": this.serialCert, "type": "sign"};
        var signString = JSON.stringify(sign);
        //var WebSocket = require('ws');
        //var ws = new WebSocket('wss://localhost:8006/', {origin: 'https://localhost:8006',rejectUnauthorized: false});
        //var ws = new WebSocket('wss://localhost:8006/');
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
    },
    getCert: function (receiveFunc, errorFunc) {
        var hashData = {"token": this.token, "type": "getCert"};
        var signString = JSON.stringify(hashData);
        //var WebSocket = require('ws');
        //var ws = new WebSocket('wss://localhost:8006/', {origin: 'https://localhost:8006',rejectUnauthorized: false});
        //var ws = new WebSocket('wss://localhost:8006/');
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
    },
    uploadCert: function (receiveFunc, errorFunc) {
        var hashData = {"token": this.token, "type": "uploadCert"};
        var signString = JSON.stringify(hashData);
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
    },
    finish: function () {
        var hashData = {"token": this.token, "type": "finish", "messageCode": this.messageCode};
        var signString = JSON.stringify(hashData);
        var ws = new WebSocket(this.url);
        ws.onopen = function () {
            ws.send(signString);
        };
        ws.onmessage = function (evt) {
            console.log("Message");
            console.log(evt);
        };
        ws.onclose = function (evt) {
            console.log("Error");
            console.log(evt);
        };
    }


};

//var idSignMessage;
//var idCertString;
//var idSerial;
//var idSignValue;
//var idSignHashValue;
//
//
//signFileXml = function () {
//    startLoading();
//    socket.getCert(receiveFunc, errorFunction);
//    return false;
//};
//
//receiveFunc = function (evt) {
//    var signValue = JSON.parse(evt.data);
//
//    if (signValue.messageCode !== "" && signValue.messageCode !== null) {
////                            alert(signValue.messageCode);
//        console.log("Message code: " + signValue.messageCode);
//        var idMessage = idSignMessage;
//        var textMessage = "#{lang['common.msg.error']}";
//        var messageType = "error";
////                            if (signValue.messageCode === "SESSION_TIME_OUT") {
////                                textMessage = "#{lang['sign.invoice.session.time.out']}";
////                            } else if (signValue.messageCode === "IS_SIGNING") {
////                                textMessage = "#{lang['create.invoice.ust-token.is.signing']}";
////                            } else if (signValue.messageCode === "FORCE_CLOSE") {
////                                textMessage = "#{lang['msg.stop.sign.usb-token']}";
////                                messageType = "info";
////                            }
//
//        var message = handleSigningErrorCode(signValue.messageCode);
//        if (message !== null && message.textMessage !== "" && message.messageType !== "") {
//            textMessage = message.textMessage;
//            messageType = message.messageType;
//
//        }
//
//        setMessagePrimeFToView(idMessage, textMessage, messageType);
//        stopLoading();
//
//    } else if (signValue.certRaw !== "" && signValue.certRaw !== null) {
////                            console.log("CertRaw: " + signValue.certRaw);
////                            console.log("signValue: " + signValue.signValue);
//        $(idCertString).val(signValue.certRaw);
//        $(idSerial).val(signValue.serial);
//        $(idSignValue).val(signValue.signValue);
//        hashFile();
//    } else {
//        var idMessage = idSignMessage;
//        var textMessage = "#{lang['transfered.cert.usb.empty']}";
//        var messageType = "error";
//        setMessagePrimeFToView(idMessage, textMessage, messageType);
//
//        socket.messageCode = 'EMPTY_TRANSFERED_CERT';
//        socket.finish();
//        clearSocket();
//        stopLoading();
//    }
//
//};
//
//errorFunction = function (event) {
//
//    var idMessage = idSignMessage;
//    var textMessage = "#{lang['common.msg.error']}";
//    var messageType = "error";
//    console.log("Error code: " + event.code);
//    if (event.code === 1000) {
//        reason = "Normal closure, meaning that the purpose for which the connection was established has been fulfilled.";
//        return;
//    } else if (event.code === 1001) {
//        reason = "An endpoint is \"going away\", such as a server going down or a browser having navigated away from a page.";
//        textMessage = "#{lang['sign.invoice.session.time.out']}";
//    } else if (event.code === 1002)
//        reason = "An endpoint is terminating the connection due to a protocol error";
//    else if (event.code === 1003)
//        reason = "An endpoint is terminating the connection because it has received a type of data it cannot accept (e.g., an endpoint that understands only text data MAY send this if it receives a binary message).";
//    else if (event.code === 1004)
//        reason = "Reserved. The specific meaning might be defined in the future.";
//    else if (event.code === 1005)
//        reason = "No status code was actually present.";
//    else if (event.code === 1006) {
//        //        reason = "The connection was closed abnormally, e.g., without sending or receiving a Close control frame";
//        reason = "Không thể kết nối đến USB token. Yêu cầu cắm USB-TOKEN và cài đặt phần mềm ký số của Viettel";
//        textMessage = "#{lang['message.einvoice.tool.not.found']}";
//    } else if (event.code === 1007)
//        reason = "An endpoint is terminating the connection because it has received data within a message that was not consistent with the type of the message (e.g., non-UTF-8 [http://tools.ietf.org/html/rfc3629] data within a text message).";
//    else if (event.code === 1008)
//        reason = "An endpoint is terminating the connection because it has received a message that \"violates its policy\". This reason is given either if there is no other sutible reason, or if there is a need to hide specific details about the policy.";
//    else if (event.code === 1009)
//        reason = "An endpoint is terminating the connection because it has received a message that is too big for it to process.";
//    else if (event.code === 1010) // Note that this status code is not used by the server, because it can fail the WebSocket handshake instead.
//        reason = "An endpoint (client) is terminating the connection because it has expected the server to negotiate one or more extension, but the server didn't return them in the response message of the WebSocket handshake. <br /> Specifically, the extensions that are needed are: " + event.reason;
//    else if (event.code === 1011)
//        reason = "A server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.";
//    else if (event.code === 1015)
//        reason = "The connection was closed due to a failure to perform a TLS handshake (e.g., the server certificate can't be verified).";
//    else
//        reason = "Unknown reason";
//    console.log(reason);
//    //    new PNotify({
//    //        title: 'ThÃ´ng bÃ¡o',
//    //        text: reason,
//    //        type: 'error'
//    //    });
//    setMessagePrimeFToView(idMessage, textMessage, messageType);
//    clearSocket();
//    stopLoading();
//};
//
//
//
//function handleSigningErrorCode(errorCode) {
//    try {
//        var message = {
//            textMessage: "",
//            messageType: ""
//        };
//        if (errorCode === "SESSION_TIME_OUT") {
//            message.textMessage = "#{lang['sign.invoice.session.time.out']}";
//            message.messageType = "error";
//        } else if (errorCode === "IS_SIGNING") {
//            message.textMessage = "#{lang['create.invoice.ust-token.is.signing']}";
//            message.messageType = "error";
//        } else if (errorCode === "FORCE_CLOSE") {
//            message.textMessage = "#{lang['msg.stop.sign.usb-token']}";
//            message.messageType = "info";
//        } else if (errorCode === "DEFAULT_CERT_NOT_IN_USB_TOKEN") {
//            message.textMessage = "#{lang['default.cert.not.in.usb.token']}";
//            message.messageType = "error";
//        } else if (errorCode === "NO_INTERNET_CONNECTION_TO_VALIDATE_DEFAUT_CERT") {
//            message.textMessage = "#{lang['default.cert.validation.connection.fail']}";
//            message.messageType = "error";
//        } else if (errorCode === "NO_INTERNET_CONNECTION") {
//            message.textMessage = "#{lang['cert.validation.connection.fail']}";
//            message.messageType = "error";
//        } else if (errorCode === "DEFAULT_CERT_HAVE_ERROR") {
//            message.textMessage = "#{lang['default.cert.error.message']}";
//            message.messageType = "error";
//        } else if (errorCode === "INVALID_DEFAULT_CERT") {
//            message.textMessage = "#{lang['default.cert.invalid']}";
//            message.messageType = "error";
//        } else if (errorCode === "NO_CERT_IN_USB") {
//            message.textMessage = "#{lang['no.cert.in.usb.token']}";
//            message.messageType = "error";
//        } else if (errorCode === "NO_INTERNET_CONNECTION_SETUP_TABLE") {
//            message.textMessage = "#{lang['cert.validation.connection.fail']}";
//            message.messageType = "error";
//        } else if (errorCode === "VALUE_EMPTY") {
//            message.textMessage = "#{lang['message.value.empty.to.sign']}";
//            message.messageType = "error";
//        } else if (errorCode === "EMPTY_INVOICE") {
//            message.textMessage = "#{lang['empty.invoice.exist']}";
//            message.messageType = "error";
//        } else if (errorCode === "NO_INTERNET_CONNECTION_TO_VALIDATE_CERT_TO_SIGN") {
//            message.textMessage = "#{lang['cert.validation.connection.fail']}";
//            message.messageType = "error";
//        } else if (errorCode === "HAVE_ERROR_WHEN_SIGING") {
//            message.textMessage = "#{lang['have.error.when.signing']}";
//            message.messageType = "error";
//        } else if (errorCode === "INVALID_CERT") {
//            message.textMessage = "#{lang['message.invalid.cert']}";
//            message.messageType = "error";
//        } else if (errorCode === "NO_USB_TOKEN_TO_SIGN") {
//            message.textMessage = "#{lang['no.usb.token']}";
//            message.messageType = "error";
//        } else if (errorCode === "MORE_THAN_ONE_USB_TOKEN_TO_SIGN") {
//            message.textMessage = "#{lang['two.usb.token.found']}";
//            message.messageType = "error";
//        } else if (errorCode === "USB-UNPLUG") {
//            message.textMessage = "#{lang['disconnect.usb.token']}";
//            message.messageType = "error";
//        } else if (errorCode === "HAVE_ERROR") {
//            message.textMessage = "#{lang['common.msg.error']}";
//            message.messageType = "error";
//        }
//
//        return message;
//
//    } catch (e) {
//    }
//    return null;
//}
//
//finishSignProcess = function (messageCode) {
//    socket.messageCode = messageCode;
//    socket.finish();
//};
//
//
//preSignFile = function () {
//    socket.dataSign = $(idSignHashValue).val();
//    socket.serialCert = $(idSerial).val();
//    socket.sign(receiveSignFileFunc, errorFunction);
//    return false;
//};
//receiveSignFileFunc = function (evt) {
//    var signValue = JSON.parse(evt.data);
//    if (signValue.messageCode !== "" && signValue.messageCode !== null) {
//        console.log("Message code: " + signValue.messageCode);
//        var idMessage = idSignMessage;
//        var textMessage = "#{lang['common.msg.error']}";
//        var messageType = "error";
//
////                            if (signValue.messageCode === "SESSION_TIME_OUT") {
////                                textMessage = "#{lang['sign.invoice.session.time.out']}";
////                            } else if (sigValue.messageCode === "SUCCESS") {
////                                textMessage = "#{lang['create-invoice-usb-token-success']}";
////                                messageType = "info";
////                            }
//
//        var message = handleSigningErrorCode(signValue.messageCode);
//        if (message !== null && message.textMessage !== "" && message.messageType !== "") {
//            textMessage = message.textMessage;
//            messageType = message.messageType;
//        }
//
//        setMessagePrimeFToView(idMessage, textMessage, messageType);
//        stopLoading();
//
//    } else if (signValue.signValue !== null && signValue.signValue.trim() !== "") {
////                            alert(sigValue.signValue);
//        $(idCertString).val(signValue.certRaw);
//        $(idSerial).val(signValue.serial);
//        $(idSignValue).val(signValue.signValue.trim());
//        insertSignature();
//    } else {
//        clearSocket();
//        stopLoading();
//    }
//
//};
//clearSocket = function () {
//    $(idCertString).val("");
//    $(idSerial).val("");
//    $(idSignValue).val("");
//    $(idSignHashValue).val("");
//    socket.dataSign = "";
//    socket.serialCert = "";
//
//};
//



//receiveFunc = function (evt) {
//    var sigValue = JSON.parse(evt.data);
//    document.getElementById("certString").value = sigValue.certRaw;
//    document.getElementById("serial").value = sigValue.serial;
//    document.getElementById("signValue").value = sigValue.signValue;
//};

//<editor-fold defaultstate="collapsed" desc="errorFunction">


//errorFunction = function (event) {
//
//    var idMessage = "#invoiceForm\\:growl";
//    var textMessage = "#{lang['common.msg.error']}";
//    var messageType = "error";
//    console.log(event.code);
//    if (event.code === 1000) {
//        reason = "Normal closure, meaning that the purpose for which the connection was established has been fulfilled.";
//    }
//    else if (event.code === 1001) {
//        reason = "An endpoint is \"going away\", such as a server going down or a browser having navigated away from a page.";
//        textMessage = "#{lang['sign.invoice.session.time.out']}";
//    } else if (event.code === 1002)
//        reason = "An endpoint is terminating the connection due to a protocol error";
//    else if (event.code === 1003)
//        reason = "An endpoint is terminating the connection because it has received a type of data it cannot accept (e.g., an endpoint that understands only text data MAY send this if it receives a binary message).";
//    else if (event.code === 1004)
//        reason = "Reserved. The specific meaning might be defined in the future.";
//    else if (event.code === 1005)
//        reason = "No status code was actually present.";
//    else if (event.code === 1006)
////        reason = "The connection was closed abnormally, e.g., without sending or receiving a Close control frame";
//        reason = "Không thể kết nối đến USB token. yêu cầu cắm USB token và cài đặt phần mềm ký của Viettel";
//    else if (event.code === 1007)
//        reason = "An endpoint is terminating the connection because it has received data within a message that was not consistent with the type of the message (e.g., non-UTF-8 [http://tools.ietf.org/html/rfc3629] data within a text message).";
//    else if (event.code === 1008)
//        reason = "An endpoint is terminating the connection because it has received a message that \"violates its policy\". This reason is given either if there is no other sutible reason, or if there is a need to hide specific details about the policy.";
//    else if (event.code === 1009)
//        reason = "An endpoint is terminating the connection because it has received a message that is too big for it to process.";
//    else if (event.code === 1010) // Note that this status code is not used by the server, because it can fail the WebSocket handshake instead.
//        reason = "An endpoint (client) is terminating the connection because it has expected the server to negotiate one or more extension, but the server didn't return them in the response message of the WebSocket handshake. <br /> Specifically, the extensions that are needed are: " + event.reason;
//    else if (event.code === 1011)
//        reason = "A server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.";
//    else if (event.code === 1015)
//        reason = "The connection was closed due to a failure to perform a TLS handshake (e.g., the server certificate can't be verified).";
//    else
//        reason = "Unknown reason";
//    console.log(reason);
////    new PNotify({
////        title: 'Thông báo',
////        text: reason,
////        type: 'error'
////    });
//    setMessagePrimeFToView(idMessage, textMessage, messageType);
//    stopLoading();
//};

//errorFunc = function (event) {
////                var reason;
//    console.log(event.code);
//    if (event.code == 1000)
//        reason = "Normal closure, meaning that the purpose for which the connection was established has been fulfilled.";
//    else if (event.code == 1001)
//        reason = "An endpoint is \"going away\", such as a server going down or a browser having navigated away from a page.";
//    else if (event.code == 1002)
//        reason = "An endpoint is terminating the connection due to a protocol error";
//    else if (event.code == 1003)
//        reason = "An endpoint is terminating the connection because it has received a type of data it cannot accept (e.g., an endpoint that understands only text data MAY send this if it receives a binary message).";
//    else if (event.code == 1004)
//        reason = "Reserved. The specific meaning might be defined in the future.";
//    else if (event.code == 1005)
//        reason = "No status code was actually present.";
//    else if (event.code == 1006)
////        reason = "The connection was closed abnormally, e.g., without sending or receiving a Close control frame";
//        reason = "Không thể kết nối đến USB token. yêu cầu cắm USB token và cài đặt phần mềm ký của Viettel";
//    else if (event.code == 1007)
//        reason = "An endpoint is terminating the connection because it has received data within a message that was not consistent with the type of the message (e.g., non-UTF-8 [http://tools.ietf.org/html/rfc3629] data within a text message).";
//    else if (event.code == 1008)
//        reason = "An endpoint is terminating the connection because it has received a message that \"violates its policy\". This reason is given either if there is no other sutible reason, or if there is a need to hide specific details about the policy.";
//    else if (event.code == 1009)
//        reason = "An endpoint is terminating the connection because it has received a message that is too big for it to process.";
//    else if (event.code == 1010) // Note that this status code is not used by the server, because it can fail the WebSocket handshake instead.
//        reason = "An endpoint (client) is terminating the connection because it has expected the server to negotiate one or more extension, but the server didn't return them in the response message of the WebSocket handshake. <br /> Specifically, the extensions that are needed are: " + event.reason;
//    else if (event.code == 1011)
//        reason = "A server is terminating the connection because it encountered an unexpected condition that prevented it from fulfilling the request.";
//    else if (event.code == 1015)
//        reason = "The connection was closed due to a failure to perform a TLS handshake (e.g., the server certificate can't be verified).";
//    else
//        reason = "Unknown reason";
//    console.log(reason);
//    new PNotify({
//        title: 'Thông báo',
//        text: reason,
//        type: 'error'
//    });
//    stopLoading();
//};

//</editor-fold>
