document.getElementById("startTransaction").addEventListener("click", displayError);
document.getElementById("viewProduct").addEventListener("click", navigateProductListing);

// when visible?
document.getElementById("createEmployee").addEventListener("click", navigateEmployeeDetail);
document.getElementById("salesReport").addEventListener("click", displayError);
document.getElementById("cashiersReport").addEventListener("click", displayError);

// need to navigate to the productListing view
// navigate to new employee detail view
function navigateEmployeeDetail() {
location.assign("https://www.w3schools.com"); // should go to employee detail webpage
}

function navigateProductListing() {
location.assign("https://misfits5-app.herokuapp.com"); // should go to product listing webpage
}

function displayError() {
  alert("Functionality has not been implemented.");
}
