import { useParams } from "react-router";
const EditBook = () => {
    const params = useParams();
    const { bookId } = params;

    return(
        <div>Edit Book</div>
    );
}

export default EditBook;