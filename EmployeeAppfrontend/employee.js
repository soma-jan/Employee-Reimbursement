let logoutBtn = document.querySelector('#logout-btn');
 //btn = document.createElement("BUTTON");
logoutBtn.addEventListener('click', () => {
  localStorage.removeItem('jwt');
    localStorage.removeItem('user_id');
    localStorage.removeItem('user_role');

    window.location = '/index.html';
});

window.addEventListener('load', (event) => {
    populateRembursementTable();
});

async function populateRembursementTable() {
    const URL = `http://localhost:8081/users/${localStorage.getItem('user_id')}/reimbursements`;
 
    let res = await fetch(URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt')}`
        }
      
    })
            let errMsg = document.querySelector("#err-msg");
            let sucessmsg = document.querySelector("#sucessmsg");
            if (res.status === 400) {
              errMsg.innerText = "No reimbursement available";
              sucessmsg.innerText = "";
            
        }
  if (res.status === 200) {
        errMsg.innerText = "";
       sucessmsg.innerText = "Reimbursement details";
      let tbody = document.querySelector('#rembursement-tbl > tbody');
      tbody.innerHTML = "";
      
      let rembursements = await res.json();
   
    for (let rembursement of rembursements) {
      
            let tr = document.createElement('tr');

            let td1 = document.createElement('td');
            td1.innerText = rembursement.firstname +" "+ rembursement.lastname;

            let td2 = document.createElement('td');
            td2.innerText = rembursement.amount;

            let td3 = document.createElement('td');
            td3.innerText = rembursement.description;

            let td4 = document.createElement('td');
            td4.innerText = (rembursement.submitteddate).split(' ')[0];

          

            let td5 = document.createElement('td');
            td5.innerText = rembursement.type;

        
            
          let td6 = document.createElement('td');
          td6.innerText = rembursement.managerFirstname ;
          td6.innerText = (rembursement.managerFirstname == "N/A" ? 'Not Approved yet' : rembursement.managerFirstname +" "+ rembursement.managerLastname);

            let td7 = document.createElement('td');
            // td7.innerText = "image btn";
            let imgElement = document.createElement('img');
            let id = rembursement.id;
            imgElement.setAttribute('src', `http://localhost:8081/reimbursements/${id}/image`);
            imgElement.style.height = '100px';
            td7.appendChild(imgElement);

            tr.appendChild(td6);
            

           let td8 = document.createElement('td');
           if (rembursement.status == "APPROVE") {
             td8.innerText = "APPROVED";
             td8.style.color = "green";
             
            }
            if (rembursement.status == "DENY") {
              td8.innerText = "DENIED"
               td8.style.color = "red";
              
            }
           if (rembursement.status == "PENDING") {
              td8.innerText ="PENDING"
             
            }
      
      
      
           // td8.innerText = rembursement.status;
            
            let td9 = document.createElement('td');
            td9.innerText =( rembursement.resolveddate).split(' ')[0];
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
             tr.appendChild(td8);
              tr.appendChild(td9);
        
          
           
            let tbody = document.querySelector('#rembursement-tbl > tbody');
      tbody.appendChild(tr);
    
      
       
       
    }
    
    }
}
var modal = document.getElementById("myModal");
var btnmodal = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];
btnmodal.onclick = function() {
  modal.style.display = "block";
}
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

/*------validation----*/
let addbtn = document.querySelector('#addbtn');

addbtn.addEventListener('click', async (e) => {
  

 e.preventDefault();
    let amount = document.querySelector('#amount').value;
    let desc = document.querySelector('#desc').value;
  let image = document.querySelector('#myfile');
  let type = document.querySelector('#type').value;
  
 
 
  let formData = new FormData();
  formData.append('image', image.files[0]);
 
  formData.append('reimb_amount', amount);
  formData.append('reimb_desc', desc);
  formData.append('reimb_type', type.toUpperCase());
  let URL =`http://localhost:8081/users/${localStorage.getItem('user_id')}/reimbursement`
  try {
        let res = await fetch(URL, {
            // credentials: 'include', // Only for HttpSession authorization
            method: 'POST',
            body: formData,
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt')}`
            }
        });
   
    if (res.status === 201) {
       console.log(res.status);
      let addsucess = document.querySelector('#addsucess');
      addsucess.innerText = "Reimbursement added successfully..!"
      addsucess.style.color = "green";
      
      populateRembursementTable();
    }
    } catch (e) {
        console.log(e);
    }
    
})

 
    
 /* let errorMsg = "";
  errorElement.innerText = "";
  errorElement.style.color = 'none';

  const amount = document.querySelector('#amount').value;
  console.log(addbtn);
    const desc = document.querySelector('#desc').value;
    if ( amount <= 0) {
    
      let errorElement = document.querySelector('#error-amt');
       errorMsg = "Enter amount";
      errorElement.innerText = errorMsg;
      errorElement.style.color = 'red';
      return false;
    
  }
  if (desc=="") {
      console.log(errorMsg);
      let errorElement = document.querySelector('#desc');
      errorElement.innerText = errorMsg;
    errorElement.style.color = 'red';
    return false;
    }*/


    
