class DoingView{

constructor(task){
 this.task = task;
}

delete =()=>{

}

upfase =()=>{

}

downfase = ()=>{
    
}

render = () =>{
    let component = document.createElement('div');
    let task = document.createElement('p');
    let fecha = document.createElement('small');
    let downBt = document.createElement('button');
    let upBt = document.createElement('button');
    let deleteBt = document.createElement('button');


    downBt.innerHTML = '<<';
    downBt.className = 'downBt';
    upBt.innerHTML = '>>';
    upBt.className = 'upBt';
    deleteBt.innerHTML = 'x';
    deleteBt.className = 'deleteBt';
    task.innerHTML = this.task.task;
    fecha.innerHTML = this.task.fecha;

    component.appendChild(fecha);
    component.appendChild(task);
    
   //comportaminetos
   deleteBt.addEventListener('click', this.delete);
   upBt.addEventListener('click', this.upfase);
   downBt.addEventListener('click', this.downfase);

    return component;
}

}