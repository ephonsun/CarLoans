function checkPassword() {
   
        var pass = document.getElementById("disbook_password").value;
        
		var confirm = document.getElementById("disbook_passwordConfirm").value;
		
		if (pass != confirm)
        window.alert("Passwords do not match.");
	    
}

function checkInput() {
	
	function checkPassStrength(passwor) {
		
		
		function scorePassword(password) {
		    var score = 0;
		    if (!password)
		        return score;

		    // award every unique letter until 5 repetitions
		    var letters = new Object();
		    for (var i=0; i<password.length; i++) {
		        letters[password[i]] = (letters[password[i]] || 0) + 1;
		        score += 5.0 / letters[password[i]];
		    }

		    // bonus points for mixing it up
		    var variations = {
		        digits: /\d/.test(password),
		        lower: /[a-z]/.test(password),
		        upper: /[A-Z]/.test(password),
		        nonWords: /\W/.test(password),
		    }

		    variationCount = 0;
		    for (var check in variations) {
		        variationCount += (variations[check] == true) ? 1 : 0;
		    }
		    score += (variationCount - 1) * 10;

		    return parseInt(score);
		}
		
		
	    var score = scorePassword(passwor);
	    if (score > 80)
	        return "strong";
	    if (score > 60)
	        return "good";
	    if (score <= 30)
	        return "weak";

	}


	function validateEmail(elementValue) {    
		
		   var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;  
		   
		   return emailPattern.test(elementValue);   
	}
	
	function isInt(value) {

	    var er = /^-?[0-9]+$/;

	    return er.test(value);
	}
	  
    var pass = document.getElementById("disbook_password").value;
    
	var confirm = document.getElementById("disbook_passwordConfirm").value;
	
	var profile = document.getElementById("profileName").value;
	
	var user = document.getElementById("username").value;
	
	var email = document.getElementById("Email").value;
	
	var age = document.getElementById("user_Age").value;
	
	if (profile == "") {
     window.alert("You must enter a profile name.");
     return false;
	}
	
	if (!isInt(age)) {
		window.alert("The DisBook user valid age must be an integer number between 15-89.");
		return false;
	}
	
	if (isInt(age)) {
		if ((age <= 14) || (age >= 90)) {
			window.alert("The DisBook user valid age is between 15-89.");
			return false;
		}
	}
	
	var boolean = validateEmail(email);
	
	if (!boolean) {
		window.alert("You must enter a valid email account name.");
		return false;
	}
	
	if (user == "") { 
	   window.alert("You must enter a user name.");
	   return false;
	}
	
	if (pass == "") {
		 window.alert("You must provide a password.");
	      return false;
	}
	
	var passwordstrengthcheck = checkPassStrength(pass);
	
	if (passwordstrengthcheck == "weak") {
		window.alert("Weak password,Try another.");
		return false;
	} 	else 
		window.alert(passwordstrengthcheck);
	
	if (pass != confirm) {
      window.alert("Passwords do not match.");
      return false;
	}
	
	
	
    
}