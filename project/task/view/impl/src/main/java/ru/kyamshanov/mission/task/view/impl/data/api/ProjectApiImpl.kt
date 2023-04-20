package ru.kyamshanov.mission.task.view.impl.data.api

import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.task.view.impl.data.model.CreateSubTaskRqDto
import ru.kyamshanov.mission.task.view.impl.data.model.CreateSubTaskRsDto
import ru.kyamshanov.mission.task.view.impl.data.model.GetSubTaskRsDto
import ru.kyamshanov.mission.task.view.impl.data.model.GetTaskRsDto
import ru.kyamshanov.mission.task.view.impl.data.model.SetExecutionResultRqDto
import ru.kyamshanov.mission.task.view.impl.data.model.SubtaskDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun getTask(taskId: String): GetTaskRsDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/private/task/get") {
            contentType(ContentType.Application.Json)
            parameter("task", taskId)
        }.retrieveBody()
    }

    override suspend fun setExecutionResult(body: SetExecutionResultRqDto): Unit = withContext(Dispatchers.IO) {
        requestFactory.post("/project/private/subtask/result") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.retrieveBody()
    }

    override suspend fun getSubtaskByTaskId(taskId: String): GetSubTaskRsDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/private/subtasks") {
            contentType(ContentType.Application.Json)
            parameter("taskId", taskId)
        }.retrieveBody()
    }

    override suspend fun createSubtask(body: CreateSubTaskRqDto): CreateSubTaskRsDto = withContext(Dispatchers.IO) {
        requestFactory.post("/project/private/subtask") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.retrieveBody()
    }

    override suspend fun getSubtaskBySubtaskId(subtaskId: String): SubtaskDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/private/subtask") {
            contentType(ContentType.Application.Json)
            parameter("id", subtaskId)
        }.retrieveBody()
    }
}