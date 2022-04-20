loginBtn = document.querySelector('#login-btn');

loginBtn.addEventListener('click', async () => {
    let usernameInput = document.querySelector('#username');
    let passwordInput = document.querySelector('#password');
  
    const URL = 'http://localhost:8081/login';


    const jsonString = JSON.stringify({
        "username": usernameInput.value,
        "password": passwordInput.value
    });
 
    let res = await fetch(URL, {
        method: 'POST',
        body: jsonString
    })
    

   
      if (res.status === 200) {
        let user = await res.json();

        
        
         
          
         
          let token = res.headers.get('Token');
       
        localStorage.setItem('jwt', token);
        localStorage.setItem('user_id', user.id); // Keep track of the user id in the localStorage
        localStorage.setItem('user_role', user.userRole);

        if (user.userRole === 'MANAGER') {
            window.location = '/manager.html';
        } else if (user.userRole === 'EMPLOYEE') {
            window.location = '/employee.html';
        }
    } else {
        let errorMsg = await res.text();
        console.log(errorMsg);

        let errorElement = document.querySelector('#error-msg');
        errorElement.innerText = errorMsg;
        errorElement.style.color = 'red';
    }
});