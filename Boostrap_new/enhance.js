window.addEventListener('scroll',()=>{
    let content = document.querySelector('.rightAbout')
    let contentPos = content.getBoundingClientRect().top;
    let screenPos = window.innerHeight+4;

    if(contentPos < screenPos)
    {
      content.classList.add('active');
    }
    else{
        content.classList.remove('active');
    }
});


function checkEmail()
{
  var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  var email =  document.getElementById("Email").value;

  if(validRegex.test(email))
  {
    document.getElementById("check").style.color = "green";
    document.getElementById("check").innerHTML = "Email valid"
    return true;
  }
  else{
    document.getElementById("check").style.color = "red"; // Change the font color to red if email isn't valid.
    document.getElementById("check").innerHTML = "Please enter a valid email address"
    return false;
  }
}



window.addEventListener('load', function(){
  new Glider(document.querySelector('.glider'), {
    slidesToShow: 1,
    dots: '#dots',
    draggable: true,
    arrows: {
      prev: '.glider-prev',
      next: '.glider-next'
    }
  });
});