class TodoView{

    constructor(task){
     this.task = task;
    }
    
    delete =()=>{

    }
    
    upfase =()=>{
    
    }
    
    render = () =>{
        let component = document.createElement('div');
        let task = document.createElement('p');
        let fecha = document.createElement('small');
        let upBt = document.createElement('button');
        let deleteBt = document.createElement('button');


        upBt.innerHTML = '>>';
        upBt.className = 'upBt';
        deleteBt.innerHTML = 'x';
        deleteBt.className = 'deleteBt';
        task.innerHTML = this.task.task;
        fecha.innerHTML = this.task.fecha;
    
        component.appendChild(fecha)
        component.appendChild(task)
        
        //comportaminetos
        deleteBt.addEventListener('click', this.delete);
        upBt.addEventListener('click', this.upfase);

        return component;
    }
}