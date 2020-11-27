class TodoView{

    constructor(task){
     this.task = task;
     this.refres = null;
    }
    
    delete =()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
           if(xhr.readyState == 4){
               if(this.refres !== null){
                   this.refres();
               }
           }
        });

        xhr.open('DELETE', 'http://localhost:8081/parcial2/api/task/delete/'+this.task.task);
        xhr.send();
    }
    
    upfase =()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                if(this.refres !== null){
                    this.refres();
                }
            }
        });

        xhr.open('PUT', 'http://localhost:8081/parcial2/api/task/subir/'+this.task.task);
        xhr.send();
    }

    render =()=>{
        let component = document.createElement('div');
        let task = document.createElement('p');
        let titulo = document.createElement('p');
        let fecha = document.createElement('small');
        let upBt = document.createElement('button');
        let deleteBt = document.createElement('button');

        titulo.innerHTML = 'TODO';
        upBt.innerHTML = '>>';
        upBt.className = 'upBt';
        deleteBt.innerHTML = 'x';
        deleteBt.className = 'deleteBt';
        task.innerHTML = this.task.task;
        fecha.innerHTML = this.task.fecha;

        component.appendChild(titulo);
        component.appendChild(fecha);
        component.appendChild(task);
        component.appendChild(upBt);
        component.appendChild(deleteBt);

        //comportaminetos
        deleteBt.addEventListener('click', this.delete);
        upBt.addEventListener('click', this.upfase);
        return component;
    }
}