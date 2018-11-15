 function validation()
{
var msg5=document.getElementById("msg5");
var password=document.getElementById("pass").value;
var regpass = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]){8,}/;
if(password.match(regpass))
{
document.getElementById("msg5").innerHTML="";

}
else
{
document.getElementById("msg5").innerHTML="invalid password";
return false;
}
 //----------------------------------------------------------
 
 var name=document.getElementById("name").value;
 var regname=/[a-z]/;
 if(name.match(regname))
 {
document.getElementById("msg1").innerHTML="";
}
else
{
document.getElementById("msg1").innerHTML="invlaid name";
return false;
 }
 }
 
 function hide()
 {
 var post=document.getElementById("post1").value;
 var lab1=document.getElementById("lab1");
 var lab2=document.getElementById("lab2");
 var course=document.getElementById("course2");
 var year=document.getElementById("year");
	var result=post.localeCompare("student");
	if(result==0)
	{
		course.style.display="block";
		year.style.display="block";
		lab1.style.display="block";
		lab2.style.display="block";
	}
	else{
	course.style.display="none";
	year.style.display="none";
	lab1.style.display="none";
	lab2.style.display="none";
	}
 }