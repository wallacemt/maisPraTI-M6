import { useEffect, useState } from "react"
import axios from "axios"
import { Button, Container, Form, ListGroup } from 'react-bootstrap'

const API_URL = "http://localhost:8080/api/tasks"

export const TaskList = () => {
    const [tasks, setTasks] = useState([])
    const [newTask, setNewTask] = useState("")

    useEffect(() => {
        axios.get(API_URL)
            .then(response => setTasks(response.data))
            .catch(error => console.error("Erro ao buscar tarefas: ", error))
    }, [])

    const addTask = () => {
        if (newTask.trim() === "") return

        axios.post(API_URL, { title: newTask, completed: false })
            .then(response => {
                setTasks([...tasks, response.data])
                setNewTask("")
            })
            .catch(error => console.error("Erro ao adicionar a tarefa", error))
    }

    const deleteTask = (id) => {
        axios.delete(`${API_URL}/${id}`)
            .then(() => {
                setTasks(tasks.filter(task => task.id !== id))
            })
            .catch(error => console.error("Erro ao remover tarefa: ", error))
    }

    return (
        <Container className="mt-5">
            <h2 className="text-center">Lista de Tarefas</h2>

            <Form className="d-flex mb-3">
                <Form.Control
                    type="text"
                    placeholder="Nova tarefa"
                    value={newTask}
                    onChange={(event) => setNewTask(event.target.value)}
                    className="me-2"
                    style={{ width: "75%" }}
                />
                <Button variant="primary" onClick={addTask}>Adicionar</Button>
            </Form>

            <ListGroup>
                {tasks.map(task => (
                    <ListGroup.Item key={task.id} className="d-flex justify-content-between align-items-center">
                        <span style={{ width: "75%" }}>{task.title}</span>
                        <Button variant="danger" onClick={() => deleteTask(task?.id)}>Deletar</Button>
                    </ListGroup.Item>
                ))}
            </ListGroup>
        </Container>
    )
}

