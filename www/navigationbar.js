module.exports = {
   
    getNavbarHeight: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'NavBar',
            'getNavBarHeight',
            []
        ); 
    }
};
