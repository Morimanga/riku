# ADDONS

**GET** addons/get-all-addons

Description: None

Query params: None

**GET** addons/get-comics

Description: None

Query params (all required): remote_id - String, addon_id - Int

**GET** addons/get-comics-chapter

Description: None

Query params (all required): remote_id - String, chapter_id - Integer, addon_id - Int

**GET** addons/get-latest-comics

Description: None

Query params (all required): page - Integer, addon_id - Int

**GET** addons/search-comics

Description: None

Query params (required: Page, Sort, AddonId): page - Integer, sort - Boolean, addon_id - Int, name - String, Years - List<Integer>, Genres - List<String>

---

# FOLDERS


**GET** folder/all-folders

Description: None

Query params: None

**GET** folder/get-by-id

Description: None

Query params: id - Int

**POST** folder/edit-comics

Description: Edit comics is "upsert" function. It means update or insert.
To add a title, set "local_id" value to 0

Query params (all required): folder_id - Integer

Body:

```json
{
  "local_id": 1,
  "remote_id": "",
  "addon_id": 1,
  "name": "",
  "poster": "",
  "last_read_chapter": 1
}
```

**DELETE** folder/delete-comics

Description: None

Query params (all required): local_comics_id - Integer

**POST** folder/edit-folder

Description: Edit folder is "upsert" function. It means update or insert
To add a folder, set "id" value to 0.

Query params: None

Body:
```json
{
  "id": 1,
  "name": ""
}
```

**DELETE** folder/delete-folder

Description: None

Query params (all required): id - Integer