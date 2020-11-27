const task = document.getElementById("task");

const bt = document.getElementById("insert");

const conte1 = document.getElementById('TODO');
const conte2 = document.getElementById('DOING');
const conte3 = document.getElementById('DONE');


const agregar =()=>{
    let taskDTO = {
        fase: 1,
        task: task.value,
        fecha:'N/A'
    };
    console.log(JSON.stringify(taskDTO));
    let xhr = new XMLHttpRequest();
    if(xhr.readyState == 4){
        getAll();
    }
    xhr.open('POST', 'http://localhost:8081/parcial2/api/task/create');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(taskDTO));

}

bt.addEventListener('click', agregar);


const getAllTaskTODO = ()=>{
    conte1.innerHTML = '';
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{

        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            for(let i = 0; i<response.length; i++){
                let taskDTO = response[i]
                let view = new TodoView(taskDTO);
                view.refres = getAll;
                conte1.appendChild(view.render());

            }
        }
    });
    xhr.open('GET', 'http://localhost:8081/parcial2/api/task/allTODO');
    xhr.send();
}

const getAllTaskDOING = ()=>{
    conte2.innerHTML = '';
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{

        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            for(let i = 0; i<response.length; i++){
                let taskDTO = response[i];
                let view = new DoingView(taskDTO);
                view.refres = getAll;
                conte2.appendChild(view.render());
            }
        }
    });
    xhr.open('GET', 'http://localhost:8081/parcial2/api/task/allDOING');
    xhr.send();
}

const getAllTaskDONE = ()=>{
    conte3.innerHTML = '';
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{

        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            for(let i = 0; i<response.length; i++){
                let taskDTO = response[i];
                let view = new DoneView(taskDTO);
                view.refres = getAll;
                conte3.appendChild(view.render());
            }
        }
    });
    xhr.open('GET', 'http://localhost:8081/parcial2/api/task/allDONE');
    xhr.send();
}

const getAll =()=>{
    console.log('sisasa')
    getAllTaskTODO();
    getAllTaskDOING();
    getAllTaskDONE();
}
getAll();
