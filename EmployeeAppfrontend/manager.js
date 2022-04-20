let logoutBtn = document.querySelector('#logout-btn');

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
    const URL = `http://localhost:8081/rembursements`;
         try {
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
                let rembursements = await res.json();
                let tbody = document.querySelector('#rembursement-tbl > tbody');
                tbody.innerHTML = "";

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
                    td6.innerText = (rembursement.managerFirstname == 'N/A' ? 'Not Approved yet' : rembursement.managerFirstname + " "+rembursement.managerLastname);       

                    let td7 = document.createElement('td');
                    let imgElement = document.createElement('img');
                     let id = rembursement.id;
                     imgElement.setAttribute('src', `http://localhost:8081/reimbursements/${id}/image`);
                     imgElement.style.height = '100px';
                     td7.appendChild(imgElement);
                     tr.appendChild(td7);


                    let td8 = document.createElement('td');
                    if (rembursement.status === "PENDING") {
                
                        let select = document.createElement("select");
                        let btnUpdate = document.createElement("button");
                        btnUpdate.innerText = "UPDATE"
                        btnUpdate.id = "dbutton";
                        let strText = "";
                        let opt1 = document.createElement("option");
                        opt1.selected;
                        opt1.innerText = "SELECT";
                        let opt2 = document.createElement("option");
                        opt2.innerText = "APPROVE";     
                        let opt3 = document.createElement("option");
                        opt3.innerText = "DENY"
                        select.appendChild(opt1);
                        select.appendChild(opt2);
                        select.appendChild(opt3);
                        td8.appendChild(select);
             btnUpdate.addEventListener('click', async () => {
                        strText = select.value;
                         if (strText == 'SELECT')
                        {
                            alert("Please select a valid status")
                        }else
                        {
                            btnUpdate.innerText = "Updating...."
                            try {
                                let res = await fetch(`http://localhost:8081/rembursement/${rembursement.id}?status=${strText}`, {
                                    // credentials: 'include' // HttpSession based login + authorization
                                    method: 'PATCH',
                                    headers: {
                                    'Authorization': `Bearer ${localStorage.getItem('jwt')}` // Include our JWT into the request
                                    }
                                });
                        if (res.status === 201) {
                            populateRembursementTable();
                                }
                            } catch (e)
                            {
                                console.log(e);
                            }
                        } });
                        td8.appendChild(btnUpdate);
                    } else {
                        if (rembursement.status == "APPROVE") {
                            td8.innerText = "APPROVED";
                            td8.style.color = "green";
                        }
                        if (rembursement.status == "DENY") {
                            td8.innerText = "DENIED";
                            td8.style.color = "red";
                        }
                
                 
                }
          
           

            
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
           
            

                //let tbody = document.querySelector('#rembursement-tbl > tbody');
                tbody.appendChild(tr);
            }
    
        }


   
    
    } catch (e) {
        console.log(e);
    
    }
}
