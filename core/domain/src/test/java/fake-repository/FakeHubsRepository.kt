package fakeRepository

import com.example.domain.model.HubModel
import com.example.domain.repository.HubRepository

class FakeHubsRepository: HubRepository {

    val fakeDb = mutableListOf<HubModel>(
        HubModel(
            id = "1",
            title = "TestHub",
            category = "Test",
            description = "Some description",
            hubImage = "Image",
            users = listOf(),
            adminName = "Admin",
            adminId = "1",
            isOpen = false,
            stages = emptyList(),
            chats = TODO(),
            notification = TODO()
        )

    )

    override suspend fun createHub(hub: HubModel) {

    }

    override suspend fun getHubById(id: String): HubModel {
        TODO("Not yet implemented")
    }

    override suspend fun getAllHubs(): List<HubModel> {
        TODO("Not yet implemented")
    }
}