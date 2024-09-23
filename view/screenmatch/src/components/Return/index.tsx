import IconReturn from '@/assets/img/icon_return.svg';
import styles from './Return.module.scss';
import { useNavigate } from 'react-router-dom';


export default function Return() {
  const navigate = useNavigate();

  return (
    <section className={ styles.container } 
      onClick={() => navigate(-1)}>
      <img className={ styles.container__img } src={ IconReturn } alt="Icone voltar" />
      <p className={ styles.container__text }>Voltar</p>
    </section>
  );
}