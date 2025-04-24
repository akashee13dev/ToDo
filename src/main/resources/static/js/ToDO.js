var mail = '';
// var total = 0;
// var pending = 0 ;
// var completed = 0;
// var removed : 0;
var tasks = [];

function init(){
  const urlParams = new URLSearchParams(window.location.search);
  const userMail = urlParams.get('userMail');
  if(userMail && userMail !== ''){
    mail = userMail;
    axios.get('http://localhost:8000/tasks', {
      headers:{
        "X-Mail-Auth" : userMail
      }
    }).then(response => {
      console.log(response.data);
      tasks = response.data;
      renderTasks(response.data);
    }).catch(error => {
      console.error("There was an error!", error);
      alert("Sorry There was an error! " + error);
    });
  }
  else {
    window.location.href = "home.html";
    alert("No Authentication found");
  }
}

function getIdForTask(listNumber){
  return 'listNo_'+listNumber;
}

function  appendNewTask(event , element){
    var value = element.value;
    if(value !== undefined && value !== ''){
      element.classList.remove("errorInput");
      addNewTask(value);
      //document.getElementById('totalCount').innerHTML = total;
      element.value = '';
    }
    else {
      element.classList.add("errorInput");
    }

}


function addNewTask(value){
  var task = {};
  task.status = 'OPEN'; //'completed' , 'deleted'
  task.message = value;

  axios.post('http://localhost:8000/task', task , {
    headers:{
      "X-Mail-Auth" : mail
    }
  }).then(response => {
    tasks.push(response.data);
    renderTask(response.data);
  }).catch(error => {
    console.error("There was an error!", error);
    alert("Sorry There was an error! " + error);
  });
  let isInputAlreadyAvailable = false;
}

function renderTasks(tasksList){
  for (let task of tasksList){
    renderTask(task);
  }
}

function renderTask(task){

  const  inputContainer = document.getElementById("taskContainer");

  const inputGroup = document.createElement('div');
  inputGroup.classList.add('task');
  inputGroup.id = 'Task_listNo_' + task.id;

  const completeBtn = document.createElement('img');
  completeBtn.id = 'Task_listNo_' + task.id+'_complete'
  completeBtn.src = "../icon/checkbox-component-checked-svgrepo-com.svg"
  completeBtn.className = 'deleteIcon';

  completeBtn.addEventListener('click', function() {
    markCompleted(message.id);
  });

  const message = document.createElement('div');
  message.innerText = task.message;
  message.id = 'listNo_' + task.id;
  message.className = 'list';

  const deleteBtn = document.createElement('img');
  deleteBtn.id = 'Task_listNo_' + task.id+'_delete'
  deleteBtn.src = "../icon/delete-svgrepo-com.svg"
  deleteBtn.className = 'deleteIcon';
  deleteBtn.addEventListener('click', function() {
    deleteTask(message.id);
  });

  const reOpenTaskBtn = document.createElement('img');
  reOpenTaskBtn.id = 'Task_listNo_' + task.id+'_reopen'
  reOpenTaskBtn.src = "../icon/open-svgrepo-com.svg"
  reOpenTaskBtn.className = 'openIcon hide';
  reOpenTaskBtn.addEventListener('click', function() {
    reOpenTask(message.id);
  });

  inputGroup.appendChild(completeBtn);
  inputGroup.appendChild(message);
  inputGroup.appendChild(deleteBtn);
  inputGroup.appendChild(reOpenTaskBtn);
  inputContainer.appendChild(inputGroup);
}

function markCompleted(id){
  const  inputContainer = document.getElementById('Task_'+id);
  inputContainer.classList.add("completed");
  hideIcons(id);
  console.log("Removing "+id);
}

function deleteTask(id){
  const  inputContainer = document.getElementById('Task_'+id);
  inputContainer.classList.add("removed");
  hideIcons(id);
  console.log("Removing "+id);
}

function reOpenTask(id){
  const  inputContainer = document.getElementById('Task_'+id);
  inputContainer.classList.remove("removed");
  inputContainer.classList.remove("completed");
  openIcons(id);
  console.log("Opended "+id);
}



function hideIcons(id){
  const  completeBtn = document.getElementById('Task_'+id+'_complete');
  completeBtn.classList.add("hide");
  const  deleteBtn = document.getElementById('Task_'+id+'_delete');
  deleteBtn.classList.add("hide");
  const  openIcon = document.getElementById('Task_'+id+'_reopen');
  openIcon.classList.remove("hide");
}


function openIcons(id){
  const  completeBtn = document.getElementById('Task_'+id+'_complete');
  completeBtn.classList.remove("hide");
  const  deleteBtn = document.getElementById('Task_'+id+'_delete');
  deleteBtn.classList.remove("hide");
  const  openIcon = document.getElementById('Task_'+id+'_reopen');
  openIcon.classList.add("hide");
}

init();
